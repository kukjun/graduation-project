package spms.dao;

import spms.vo.Location;

import javax.sql.DataSource;

public interface LocationDao {
    public void setDataSource(DataSource dataSource);

    public Location selectOne(int locationNo) throws Exception;

    public int insert(Location location) throws Exception;

    public int update(Location location) throws Exception;

    public int delete(int locationNo) throws Exception;

    public Location exist(int x, int y) throws Exception;

}
