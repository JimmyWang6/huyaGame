package hyext.ebs.examples.websocket;

public class User {
    private int senderLevel;
    private String unionId;
    private String badgeName;
    private String senderAvatarUrl;
    private int showMode;
    private int fansLevel;
    private String sendNick;
    private int nobleLevel;
    private String content;
    private int roomId;
    public boolean senderGender;

    public int getSenderLevel() {
        return senderLevel;
    }

    public void setSenderLevel(int senderLevel) {
        this.senderLevel = senderLevel;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getSenderAvatarUrl() {
        return senderAvatarUrl;
    }

    public void setSenderAvatarUrl(String senderAvatarUrl) {
        this.senderAvatarUrl = senderAvatarUrl;
    }

    public int getShowMode() {
        return showMode;
    }

    public void setShowMode(int showMode) {
        this.showMode = showMode;
    }

    public int getFansLevel() {
        return fansLevel;
    }

    public void setFansLevel(int fansLevel) {
        this.fansLevel = fansLevel;
    }

    public String getSendNick() {
        return sendNick;
    }

    public void setSendNick(String sendNick) {
        this.sendNick = sendNick;
    }

    public int getNobleLevel() {
        return nobleLevel;
    }

    public void setNobleLevel(int nobleLevel) {
        this.nobleLevel = nobleLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public boolean isSenderGender() {
        return senderGender;
    }

    public void setSenderGender(boolean senderGender) {
        this.senderGender = senderGender;
    }

    @Override
    public String toString() {
        return "User{" +
                "senderLevel=" + senderLevel +
                ", unionId='" + unionId + '\'' +
                ", badgeName='" + badgeName + '\'' +
                ", senderAvatarUrl='" + senderAvatarUrl + '\'' +
                ", showMode=" + showMode +
                ", fansLevel=" + fansLevel +
                ", sendNick='" + sendNick + '\'' +
                ", nobleLevel=" + nobleLevel +
                ", content='" + content + '\'' +
                ", roomId=" + roomId +
                ", senderGender=" + senderGender +
                '}';
    }
}
