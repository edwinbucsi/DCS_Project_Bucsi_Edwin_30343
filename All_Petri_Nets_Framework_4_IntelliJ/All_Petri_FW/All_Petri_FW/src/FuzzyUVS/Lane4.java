package FuzzyUVS;

import java.util.ArrayList;
import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFuzzy;
import DataObjects.DataTransfer;
import DataOnly.FLRS;
import DataOnly.FV;
import DataOnly.Fuzzy;
import DataOnly.PlaceNameWithWeight;
import DataOnly.TransferOperation;
import Enumerations.FZ;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Lane4 {
    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Lane 4";
        pn.NetworkPort = 1084;

        FLRS flrsMove = new FLRS(new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL));

        Fuzzy greenSignal = new Fuzzy(0.0F);

        DataFuzzy p_i = new DataFuzzy();
        p_i.SetName("P_i");
        p_i.SetValue(new Fuzzy(0.4F));
        pn.PlaceList.add(p_i);

        DataFuzzy p_l = new DataFuzzy();
        p_l.SetName("P_l");
        pn.PlaceList.add(p_l);

        DataFuzzy lane2 = new DataFuzzy();
        lane2.SetName("lane2");
        pn.PlaceList.add(lane2);

        DataFuzzy p_tl = new DataFuzzy();
        p_tl.SetName("P_TL");
        p_tl.SetValue(greenSignal);
        pn.PlaceList.add(p_tl);

        DataTransfer p_out = new DataTransfer();
        p_out.SetName("P_out");
        p_out.Value = new TransferOperation("localhost", "1080", "P_i4");
        pn.PlaceList.add(p_out);

        DataFuzzy green = new DataFuzzy();
        green.SetName("Green");
        green.SetValue(greenSignal);
        pn.ConstantPlaceList.add(green);

        DataFuzzy p_o4 = new DataFuzzy();
        p_o4.SetName("P_o4");
        pn.PlaceList.add(p_o4);

        DataFuzzy p_exit4 = new DataFuzzy();
        p_exit4.SetName("P_Exit4");
        pn.PlaceList.add(p_exit4);

        PetriTransition t_in = new PetriTransition(pn);
        t_in.TransitionName = "t_in";
        t_in.InputPlaceName.add("P_i");

        Condition tinCt1 = new Condition(t_in, "P_i", TransitionCondition.NotNull);
        GuardMapping grdTin = new GuardMapping();
        grdTin.condition = tinCt1;

        ArrayList<PlaceNameWithWeight> inputTin = new ArrayList<>();
        inputTin.add(new PlaceNameWithWeight("P_i", 1F));

        ArrayList<String> outputTin = new ArrayList<>();
        outputTin.add("P_l");

        grdTin.Activations.add(new Activation(t_in, flrsMove, inputTin, TransitionOperation.FLRS, outputTin));
        t_in.GuardMappingList.add(grdTin);
        t_in.Delay = 0;
        pn.Transitions.add(t_in);

        PetriTransition t_send = new PetriTransition(pn);
        t_send.TransitionName = "t_send";
        t_send.InputPlaceName.add("P_l");

        Condition tsendCt1 = new Condition(t_send, "P_l", TransitionCondition.NotNull);
        GuardMapping grdTsend = new GuardMapping();
        grdTsend.condition = tsendCt1;

        ArrayList<PlaceNameWithWeight> inputTsend = new ArrayList<>();
        inputTsend.add(new PlaceNameWithWeight("P_l", 1F));

        ArrayList<String> outputTsend = new ArrayList<>();
        outputTsend.add("lane2");

        grdTsend.Activations.add(new Activation(t_send, flrsMove, inputTsend, TransitionOperation.FLRS, outputTsend));
        t_send.GuardMappingList.add(grdTsend);
        t_send.Delay = 0;
        pn.Transitions.add(t_send);

        PetriTransition t_out = new PetriTransition(pn);
        t_out.TransitionName = "t_out";
        t_out.InputPlaceName.add("lane2");
        t_out.InputPlaceName.add("P_TL");

        Condition toutCt1 = new Condition(t_out, "lane2", TransitionCondition.NotNull);
        Condition toutCt2 = new Condition(t_out, "P_TL", TransitionCondition.Equal, "Green");
        toutCt1.SetNextCondition(LogicConnector.AND, toutCt2);

        GuardMapping grdTout = new GuardMapping();
        grdTout.condition = toutCt1;

        grdTout.Activations.add(new Activation(t_out, "lane2", TransitionOperation.SendOverNetwork, "P_out"));
        grdTout.Activations.add(new Activation(t_out, "P_TL", TransitionOperation.Move, "P_TL"));

        t_out.GuardMappingList.add(grdTout);
        t_out.Delay = 0;
        pn.Transitions.add(t_out);

        PetriTransition t_1 = new PetriTransition(pn);
        t_1.TransitionName = "t_1";
        t_1.InputPlaceName.add("P_o4");

        Condition t1Ct1 = new Condition(t_1, "P_o4", TransitionCondition.NotNull);
        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = t1Ct1;

        ArrayList<PlaceNameWithWeight> inputT1 = new ArrayList<>();
        inputT1.add(new PlaceNameWithWeight("P_o4", 1F));

        ArrayList<String> outputT1 = new ArrayList<>();
        outputT1.add("P_Exit4");

        grdT1.Activations.add(new Activation(t_1, flrsMove, inputT1, TransitionOperation.FLRS, outputT1));
        t_1.GuardMappingList.add(grdT1);
        t_1.Delay = 0;
        pn.Transitions.add(t_1);

        System.out.println("Lane 4 started \n ------------------------------");
        pn.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}