package learn.dataStructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 吴飞群
 * @createTime 2022/06/02
 */
@Data
@AllArgsConstructor
public class TreeNode {
    private Integer id;
    private Integer pid;
    private String name;
}
