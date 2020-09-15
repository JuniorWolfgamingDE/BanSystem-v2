package net.coalcube.bansystem.core.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface BanManager {
    void log(String action, String creator, String target, String note) throws SQLException;
    void kick(UUID player, String creator) throws SQLException;
    void kick(UUID player, UUID creator) throws SQLException;
    void kick(UUID player, String creator, String reason) throws SQLException;
    void kick(UUID player, UUID creator, String reason) throws SQLException;
    void ban(UUID player, long time, UUID creator, Type type, String reason, InetAddress v4adress) throws IOException, SQLException;
    void ban(UUID player, long time, UUID creator, Type type, String reason) throws IOException, SQLException;
    void ban(UUID player, long time, String creator, Type type, String reason, InetAddress v4adress) throws IOException, SQLException;
    void ban(UUID player, long time, String creator, Type type, String reason) throws IOException, SQLException;
    void unBan(UUID player, UUID unBanner, String reason) throws IOException, SQLException;
    void unBan(UUID player, String unBanner, String reason) throws IOException, SQLException;
    void unBan(UUID player, UUID unBanner) throws IOException, SQLException;
    void unBan(UUID player, String unBanner) throws IOException, SQLException;
    void unMute(UUID player, UUID unBanner, String reason) throws IOException, SQLException;
    void unMute(UUID player, String unBanner, String reason) throws IOException, SQLException;
    void unMute(UUID player, UUID unBanner) throws IOException, SQLException;
    void unMute(UUID player, String unBanner) throws IOException, SQLException;
    void deleteHistory(UUID player) throws SQLException;
    void setIP(UUID player, InetAddress address) throws SQLException;
    String getBanReason(UUID player, Type type) throws SQLException, ExecutionException, InterruptedException;
    Long getEnd(UUID player, Type type) throws SQLException, ParseException, ExecutionException, InterruptedException;
    String getBanner(UUID player, Type type) throws SQLException, ExecutionException, InterruptedException;
    Long getRemainingTime(UUID player, Type type) throws SQLException, ParseException, ExecutionException, InterruptedException;
    String getReason(UUID player, Type type) throws SQLException, ExecutionException, InterruptedException;
    int getLevel(UUID player, String reason) throws UnknownHostException, SQLException, ExecutionException, InterruptedException;
    Long getCreationDate(UUID player, Type type) throws SQLException, ParseException, ExecutionException, InterruptedException;
    List<History> getHistory(UUID player) throws UnknownHostException, SQLException, ParseException, ExecutionException, InterruptedException;
    List<UUID> getBannedPlayersWithSameIP(InetAddress address) throws SQLException, ExecutionException, InterruptedException;
    boolean hasHistory(UUID player) throws UnknownHostException, SQLException, ExecutionException, InterruptedException;
    boolean hasHistory(UUID player, String reason) throws UnknownHostException, SQLException, ExecutionException, InterruptedException;
    boolean isBanned(UUID player, Type type) throws SQLException, ExecutionException, InterruptedException;
    boolean isSetIP(UUID player) throws SQLException, ExecutionException, InterruptedException;
}