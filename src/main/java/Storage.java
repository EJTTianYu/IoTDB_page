public class Storage {
    private String groupName;
    private int serNum;
    public int num=200;

    public void setGroupName(String groupName){
        this.groupName=groupName;
    }
    public void setSerNum(int serNum){
        this.serNum=serNum;
    }
    public String getGroupName(){
            return this.groupName;
    }
    public int getSerNum(){
        return  this.serNum;
    }
    public Storage(String groupName,int serNum){
        this.groupName=groupName;
        this.serNum=serNum;
    }


}
