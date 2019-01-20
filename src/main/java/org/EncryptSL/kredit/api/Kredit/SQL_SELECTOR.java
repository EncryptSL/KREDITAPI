package org.EncryptSL.kredit.api.Kredit;

import org.EncryptSL.kredit.api.KreditMainClass;
import org.EncryptSL.kredit.api.MySQL.Connector;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQL_SELECTOR {

    String PLAYER_SELECT = "SELECT * FROM KREDIT WHERE UUID = ?";
    String INSERT_PLAYER = "INSERT INTO KREDIT (PLAYER, UUID,KREDITS) VALUES (?, ?, ?)";
    String UPDATE_KREDIT = "UPDATE KREDIT SET KREDITS = ? WHERE UUID = ?";

    public boolean GET_PLAYER_EXIST(UUID uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            connection = Connector.getHikariDataSource().getConnection();
            preparedStatement = connection.prepareStatement(PLAYER_SELECT);
            preparedStatement.setString(1, uuid.toString());
            set = preparedStatement.executeQuery();
            boolean player = set.next();
            return player;
        } catch (SQLException ex)  {
            ex.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if(set != null) {
                try {
                    set.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
        return false;
    }

    public int GET_PLAYER_KREDIT(UUID uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            connection = Connector.getHikariDataSource().getConnection();
            preparedStatement = connection.prepareStatement(PLAYER_SELECT);
            preparedStatement.setString(1, uuid.toString());
            set = preparedStatement.executeQuery();
            set.next();
            return set.getInt("KREDITS");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if(set != null) {
                try {
                    set.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return 0;
    }

    public void INSERT_PLAYER(Player player, UUID uuid, int Value) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(KreditMainClass.getKreditMainClass(), new BukkitRunnable() {
            @Override
            public void run() {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                try {
                    connection = Connector.getHikariDataSource().getConnection();
                    preparedStatement = connection.prepareStatement(INSERT_PLAYER);
                    preparedStatement.setString(1, player.getName());
                    preparedStatement.setString(2, uuid.toString());
                    preparedStatement.setInt(3, Value);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

    }

    public void ADD_KREDIT(UUID uuid, int Value) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(KreditMainClass.getKreditMainClass(), new BukkitRunnable() {
            @Override
            public void run() {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                try {
                    connection = Connector.getHikariDataSource().getConnection();
                    preparedStatement = connection.prepareStatement(UPDATE_KREDIT);
                    preparedStatement.setInt(1, GET_PLAYER_KREDIT(uuid) + Value);
                    preparedStatement.setString(2, uuid.toString());
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    if(connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if(preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void SET_KREDIT(UUID uuid, int Value) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(KreditMainClass.getKreditMainClass(), new BukkitRunnable() {
            @Override
            public void run() {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                try {
                    connection = Connector.getHikariDataSource().getConnection();
                    preparedStatement = connection.prepareStatement(UPDATE_KREDIT);
                    preparedStatement.setInt(1, Value);
                    preparedStatement.setString(2, uuid.toString());
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    if(connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if(preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void REMOVE_KREDIT (UUID uuid, int Value) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(KreditMainClass.getKreditMainClass(), new BukkitRunnable() {
            @Override
            public void run() {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                try {
                    connection = Connector.getHikariDataSource().getConnection();
                    preparedStatement = connection.prepareStatement(UPDATE_KREDIT);
                    preparedStatement.setInt(1, GET_PLAYER_KREDIT(uuid) - Value);
                    preparedStatement.setString(2, uuid.toString());
                    preparedStatement.executeUpdate();
                } catch (
                        SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public int NEW_BALANCE(UUID uuid, int Value) {
        int current_balance = GET_PLAYER_KREDIT(uuid) + Value;
        return current_balance;
    }
}
