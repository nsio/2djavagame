package br.com.expurgacao.riverblaze.database.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.com.expurgacao.riverblaze.database.entity.Level;

/**
 * Created by Nysio on 07/02/2016.
 */
public class LevelDAO extends BaseDaoImpl<Level, Integer> {

    public LevelDAO(ConnectionSource cs) throws SQLException {
        super(Level.class);
        setConnectionSource(cs);
        initialize();
    }



}
