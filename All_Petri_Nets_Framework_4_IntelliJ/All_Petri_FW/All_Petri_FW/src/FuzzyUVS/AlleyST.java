package FuzzyUVS;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFuzzy;
import DataObjects.DataTransfer;
import DataOnly.Fuzzy;
import DataOnly.TransferOperation;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class AlleyST {
    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Alley ST";
        pn.NetworkPort = 1086;

        DataFuzzy p_i = new DataFuzzy();
        p_i.SetName("P_i");
        p_i.SetValue(new Fuzzy(0.2F));
        pn.PlaceList.add(p_i);

        DataTransfer p_out = new DataTransfer();
        p_out.SetName("P_out");
        p_out.Value = new TransferOperation("localhost", "1082", "P_st");
        pn.PlaceList.add(p_out);

        PetriTransition t_send = new PetriTransition(pn);
        t_send.TransitionName = "t_send";
        t_send.InputPlaceName.add("P_i");

        Condition tSendCt = new Condition(t_send, "P_i", TransitionCondition.NotNull);
        GuardMapping grdTsend = new GuardMapping();
        grdTsend.condition = tSendCt;

        grdTsend.Activations.add(new Activation(t_send, "P_i", TransitionOperation.SendOverNetwork, "P_out"));

        t_send.GuardMappingList.add(grdTsend);
        t_send.Delay = 0;
        pn.Transitions.add(t_send);

        System.out.println("Alley ST started \n ------------------------------");
        pn.Delay = 3000;
        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}