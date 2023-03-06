import java.util.ArrayList;
import java.util.List;

public class Demon {
    public int lostStamina;
    public int turnsToRecoverStamina;
    public int staminaRecoveredAfterFight;
    public int turnsInWhichFragmentsEarned;
    public boolean isDefeated = false;
    public int defeatedTurn;
    public List<Integer> fragmentsInTurn = new ArrayList<>();
    public int getFragmentsInTurn(int turn){
        return fragmentsInTurn.get(turn);
    }

    public void defeated(){
        isDefeated=true;
    }
    public void setDefeatedTurn(int turn){
        this.defeatedTurn=turn;
    }
}
