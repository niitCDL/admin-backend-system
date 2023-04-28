package top.rain.rbac.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.rain.common.utils.PageResult;
import top.rain.common.utils.Result;
import top.rain.rbac.convert.SysUserConvert;
import top.rain.rbac.entity.SysUserEntity;
import top.rain.rbac.query.SysUserQuery;
import top.rain.rbac.service.SysMenuService;
import top.rain.rbac.service.SysUserRoleService;
import top.rain.rbac.service.SysUserService;
import top.rain.rbac.vo.SysAuthVO;
import top.rain.rbac.vo.SysUserPasswordVO;
import top.rain.rbac.vo.SysUserVO;
import top.rain.security.user.SecurityUser;
import top.rain.security.user.UserDetail;

import java.util.List;

/**
 * 系统用户接口
 *
 * @author rain
 **/
@RestController
@RequestMapping("sys/user")
@AllArgsConstructor
@Tag(name = "用户管理")
public class SysUserController {


    private final SysMenuService sysMenuService;
    private final PasswordEncoder passwordEncoder;
    private final SysUserService sysUserService;
    private final SysUserRoleService sysUserRoleService;

    @PostMapping("info")
    @Operation(summary = "获取登录用户信息")
    public Result<SysAuthVO> info() {
        SysAuthVO vo = new SysAuthVO();
        UserDetail userDetail = SecurityUser.getUser();
        //1 获得用户详细信息，转成 vo
        SysUserVO user = SysUserConvert.INSTANCE.convert(userDetail);
        vo.setSysUserVO(user);
        //2 获取用户导航菜单
        vo.setNav(sysMenuService.getUserMenuList(userDetail, 0));
        //3 获得用户授权信息
        vo.setAuthority(sysMenuService.getUserAuthority(userDetail));
        return Result.ok(vo);
    }

    @PostMapping("password")
    @Operation(summary = "修改密码")
    public Result<String> password(@RequestBody @Valid SysUserPasswordVO vo) {
        // 原密码不正确
        UserDetail user = SecurityUser.getUser();
        if (!passwordEncoder.matches(vo.getOldPassword(), user.getPassword())) {
            return Result.error("原密码不正确");
        }
        // 修改密码
        sysUserService.updatePassword(user.getId(), passwordEncoder.encode(vo.getNewPassword()));
        return Result.ok();
    }

    @GetMapping("page")
    @Operation(summary = "用户数据分页")
    @PreAuthorize("hasAuthority('sys:user:page')")
    public Result<PageResult<SysUserVO>> page(@ParameterObject @Valid SysUserQuery query) {
        PageResult<SysUserVO> page = sysUserService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "获取指定用户的信息")
    @PreAuthorize("hasAuthority('sys:user:info')")
    public Result<SysUserVO> get(@PathVariable("id") Long id){
        SysUserEntity entity = sysUserService.getById(id);

        SysUserVO vo = SysUserConvert.INSTANCE.convert(entity);

        List<Long> roleIdList = sysUserRoleService.getRoleIdList(id);
        vo.setRoleIdList(roleIdList);

        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "保存用户")
    @PreAuthorize("hasAuthority('sys:user:save')")
    public Result<String> save(@RequestBody @Valid SysUserVO vo){
        if (StrUtil.isBlank(vo.getPassword())){
            Result.error("密码不能为空");
        }
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        sysUserService.save(vo);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改用户")
    @PreAuthorize("hasAuthority('sys:user:update')")
    public Result<String> update(@RequestBody @Valid SysUserVO vo) {
        if (StrUtil.isBlank(vo.getPassword())) {
            vo.setPassword(null);
        } else {
            vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        }

        sysUserService.update(vo);
        return Result.ok("修改成功");
    }

    @PostMapping("delete")
    @Operation(summary = "批量删除用户")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public Result<String> delete(@RequestBody List<Long> ids) {
        Long userId = SecurityUser.getUserId();
        if (ids.contains(userId)) {
            return Result.error("不能删除当前登录用户");
        }
        sysUserService.delete(ids);
        return Result.ok();
    }

    @PostMapping("import")
    @Operation(summary = "导入用户")
    @PreAuthorize("hasAuthority('sys:user:import')")
    public Result<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("请选择需要上传的文件");
        }
        //密码默认初始化123456
        sysUserService.importByExcel(file, passwordEncoder.encode("123456"));
        return Result.ok();
    }

    @PostMapping("export")
    @Operation(summary = "导出用户")
    @PreAuthorize("hasAuthority('sys:user:export')")
    public void export() {
        sysUserService.export();
    }


}
