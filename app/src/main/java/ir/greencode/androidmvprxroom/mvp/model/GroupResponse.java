package ir.greencode.androidmvprxroom.mvp.model;

public class GroupResponse {
    private GroupResponseResult[] result;
    private GroupResponseResultState resultState;

    public GroupResponseResult[] getResult() {
        return this.result;
    }

    public void setResult(GroupResponseResult[] result) {
        this.result = result;
    }

    public GroupResponseResultState getResultState() {
        return this.resultState;
    }

    public void setResultState(GroupResponseResultState resultState) {
        this.resultState = resultState;
    }
}
