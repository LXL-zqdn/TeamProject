package tallybook_system.dao;

import tallybook_system.bean.Classification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * @LXL
 */
public class ClassificationDao {
    /**
     * 根据收入或支出类型得到所有的分类信息
     *
     * @param classificationType 收入或支出内部的分类
     * @return 返回得到的分类信息
     */
    public List<Classification> selectByType(String classificationType) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Classification> classificationList = new ArrayList<Classification>();
        try {
            //进行数据的连接
            conn = JDBCUtils.getConnection();
            //获得Statement对象
            stmt = conn.createStatement();
            // 拼接SQL语句
            String sql = "select * from tb_classification where cType='" + classificationType + "';";
            //发送SQL语句
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Classification classification = new Classification();
                classification.setcId(rs.getInt(1));
                classification.setcName(rs.getString(2));
                classification.setcType(rs.getString(3));
                classificationList.add(classification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        return classificationList;
    }
}
