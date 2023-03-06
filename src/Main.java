import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //define info
        List<Demon> listOfDemons = new ArrayList<>();
        int turns=0;
        CharacterPandora pandora = new CharacterPandora(1, 2);
        int staminaToAdd=0;
        int demonIndex=0;

        //get info from files


        //turns
        for (int i =0;i<turns;i++){
            pandora.actualStamina+=staminaToAdd;
            if (pandora.actualStamina>pandora.maxStamina){
                pandora.actualStamina= pandora.maxStamina;
            }
            if (pandora.actualStamina<listOfDemons.get(demonIndex).lostStamina){
                continue;
            }
            listOfDemons.get(demonIndex).defeated();
            listOfDemons.get(demonIndex).defeatedTurn=i;
            demonIndex++;
        }
    }
}