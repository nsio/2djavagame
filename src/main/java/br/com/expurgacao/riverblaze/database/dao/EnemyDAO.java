package br.com.expurgacao.riverblaze.database.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.com.expurgacao.riverblaze.database.entity.Enemy;

/**
 * Created by Nysio on 07/02/2016.
 */
public class EnemyDAO extends BaseDaoImpl<Enemy, Integer> {

    public EnemyDAO(ConnectionSource cs) throws SQLException {
        super(Enemy.class);
        setConnectionSource(cs);
        initialize();
    }

}
