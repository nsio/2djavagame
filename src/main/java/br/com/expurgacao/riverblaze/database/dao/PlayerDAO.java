package br.com.expurgacao.riverblaze.database.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.com.expurgacao.riverblaze.database.entity.Player;

/**
 * Created by Nysio on 07/02/2016.
 */
public class PlayerDAO extends BaseDaoImpl<Player, Integer> {

    public PlayerDAO(ConnectionSource cs) throws SQLException {
        super(Player.class);
        setConnectionSource(cs);
        initialize();
    }

}
