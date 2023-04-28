package top.rain.rbac.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.rain.common.query.Query;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "用户查询参数")
public class SysUserQuery extends Query {
    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户名")
    private String mobile;

    @Schema(description = "用户名")
    private Integer gender;
}

