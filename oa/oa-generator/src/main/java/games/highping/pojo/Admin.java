package games.highping.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ????Ա?
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_admin")
@ApiModel(value="Admin对象", description="????Ա?")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "?ֻ????")
    private String phone;

    @ApiModelProperty(value = "סլ?绰")
    private String telephone;

    @ApiModelProperty(value = "??ϵ??ַ")
    private String address;

    @ApiModelProperty(value = "?û??")
    private String username;

    @ApiModelProperty(value = "???")
    private String password;

    @ApiModelProperty(value = "?û?ͷ?")
    private String userFace;

    @ApiModelProperty(value = "??ע")
    private String remark;

    @ApiModelProperty(value = "?Ƿ?????1 0")
    private Integer enabled;


}
