package learn.dataStructure;

import com.google.common.collect.Sets;
import learn.dataStructure.entity.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 吴飞群
 * @createTime 2022/06/02
 */
public class LearnTree {

    /**
     * 制造类似平铺的部门数据, 这里没有path的字段
     * 顶层节点的父级ID是0
     */
    private static List<TreeNode> produceData(){
        List<TreeNode> treeNodeList = new ArrayList<>();

        treeNodeList.add(new TreeNode(1,0,"百度"));
        treeNodeList.add(new TreeNode(2,1,"技术部"));
        treeNodeList.add(new TreeNode(3,1,"产品部"));
        treeNodeList.add(new TreeNode(4,2,"基础平台"));
        treeNodeList.add(new TreeNode(5,2,"业务研发平台"));
        treeNodeList.add(new TreeNode(6,4,"运维部"));

        return treeNodeList;
    }

    public static void getChildrenByParentId(List<TreeNode> totalList, List<TreeNode> childList, Set<Integer> parentIdSet){
        Set<Integer> newParentIdSet = new HashSet<>();

        for (TreeNode treeNode : totalList) {
            if (parentIdSet.contains(treeNode.getPid())){
                childList.add(treeNode);
                newParentIdSet.add(treeNode.getId());
            }
        }

        if (newParentIdSet.size() > 0){
            getChildrenByParentId(totalList, childList, newParentIdSet);
        }
    }

    public static void main(String[] args) {
        List<TreeNode> totalList = produceData();
        List<TreeNode> childList = new ArrayList<>();
        Set<Integer> parentIdSet = Sets.newHashSet(2);
        getChildrenByParentId(totalList, childList, parentIdSet);
        System.out.println(childList);
    }
}
