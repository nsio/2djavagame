package br.com.expurgacao.riverblaze.database;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import br.com.expurgacao.riverblaze.database.dao.CheckPointDAO;
import br.com.expurgacao.riverblaze.database.dao.EnemyDAO;
import br.com.expurgacao.riverblaze.database.dao.LevelDAO;
import br.com.expurgacao.riverblaze.database.dao.PlayerDAO;
import br.com.expurgacao.riverblaze.database.entity.CheckPoint;
import br.com.expurgacao.riverblaze.database.entity.Enemy;
import br.com.expurgacao.riverblaze.database.entity.Level;
import br.com.expurgacao.riverblaze.database.entity.Player;

/**
 * Created by Nysio on 07/02/2016.
 */
public class ConfigDataBase {

    private PlayerDAO playerDAO;
    private EnemyDAO enemyDAO;
    private LevelDAO levelDAO;
    private CheckPointDAO checkPointDAO;

    private DataBaseHelper dh;

    public ConfigDataBase(DataBaseHelper dh) throws SQLException {
        this.dh = dh;
        playerDAO = new PlayerDAO(dh.getConnectionSource());
        enemyDAO = new EnemyDAO(dh.getConnectionSource());
        levelDAO = new LevelDAO(dh.getConnectionSource());
        checkPointDAO = new CheckPointDAO(dh.getConnectionSource());
    }

    public void configurarDataBase() throws SQLException {

        // VERIFICAR SE EXISTE UM PLAYER, SE NÃO CRIAR NOVO PLAYER
            if(playerDAO.queryForAll().size() <= 0){
                Player p = new Player();
                p.setNome("Player One");
                int result = playerDAO.create(p);
            }

        // VERIFICAR SE EXISTEM TRÊS INIMIGOS CONFIGURADOS
            List<Enemy> enemies = enemyDAO.queryForAll();
            if(enemies.size() < 3){
                for(Enemy e : enemies){
                    enemyDAO.delete(e);
                }

                Enemy atiradorSolo = new Enemy();
                atiradorSolo.setNome("Atirador Solo");
                enemyDAO.create(atiradorSolo);

                Enemy caca = new Enemy();
                caca.setNome("Caça");
                enemyDAO.create(caca);

                Enemy helicopter = new Enemy();
                helicopter.setNome("Helicoptero");
                enemyDAO.create(helicopter);

            }

        // VERIFICAR SE O PRIMEIRO LEVEL ESTA CONFIGURADO
            if(levelDAO.queryForAll().size() <= 0){
                Level level = new Level();
                level.setNome("Level Um");
                level.setAltura(480);
                level.setLargura(5000);
                level.setIndice(1);
                levelDAO.create(level);
            }

        // VERIFICAR SE OS CHEPOINTS PARA O LEVEL UM ESTÃO CONFIGURADOS CORRETAMENTE
            List<CheckPoint> cps = checkPointDAO.queryForAll();
            if(cps.size() < 4){
                for(CheckPoint cp : cps){
                    checkPointDAO.delete(cp);
                }
                // ASSUMINDO QUE O RIVER BLAZE TEM AINDA APENAS UM LEVEL, FAZER ISSO PARA CADA LEVEL, CONFORME OS NOVOS LEVELS FOREM CRIADOS
                Level level = levelDAO.queryForId(1);

                CheckPoint cpLvl1 = new CheckPoint();
                cpLvl1.setNome("CheckPoint Level 1 - (1)");
                cpLvl1.setLevel(level);
                cpLvl1.setPosCheckPoint(1250);
                checkPointDAO.create(cpLvl1);

                CheckPoint cpLvl2 = new CheckPoint();
                cpLvl1.setNome("CheckPoint Level 1 - (2)");
                cpLvl1.setLevel(level);
                cpLvl1.setPosCheckPoint(2500);
                checkPointDAO.create(cpLvl2);

                CheckPoint cpLvl3 = new CheckPoint();
                cpLvl1.setNome("CheckPoint Level 1 - (3)");
                cpLvl1.setLevel(level);
                cpLvl1.setPosCheckPoint(3750);
                checkPointDAO.create(cpLvl3);

                CheckPoint cpLvl4 = new CheckPoint();
                cpLvl1.setNome("CheckPoint Level 1 - (4)");
                cpLvl1.setLevel(level);
                cpLvl1.setPosCheckPoint(5000);
                checkPointDAO.create(cpLvl4);

            }

    }


}
