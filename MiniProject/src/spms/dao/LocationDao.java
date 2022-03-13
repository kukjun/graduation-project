package spms.dao;

import spms.vo.Location;

import javax.sql.DataSource;

public interface LocationDao {
    public void setDataSource(DataSource dataSource);

    public Location selectOne(int locationNo) throws Exception;

    public int findLocationCode(String x, String y) throws Exception;


    int findLocationCode(String localLevel1, String localLevel2, String localLevel3) throws Exception;
}
