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

public class Lane2 {
    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Lane 2";
        pn.NetworkPort = 1082;

        FLRS flrsMove = new FLRS(new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL));
        FLRS flrsAdder = new FLRS(
                new FV(FZ.NL), new FV(FZ.NL), new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR),
                new FV(FZ.NL), new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM),
                new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL),
                new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL), new FV(FZ.PL),
                new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL), new FV(FZ.PL), new FV(FZ.PL)
        );

        Fuzzy greenSignal = new Fuzzy(0.0F);

        DataFuzzy p_r = new DataFuzzy();
        p_r.SetName("P_R");
        p_r.SetValue(new Fuzzy(0.0F));
        pn.PlaceList.add(p_r);

        DataFuzzy p_st = new DataFuzzy();
        p_st.SetName("P_st");
        pn.PlaceList.add(p_st);

        DataFuzzy lane2 = new DataFuzzy();
        lane2.SetName("lane2");
        pn.PlaceList.add(lane2);

        DataFuzzy lane2_ = new DataFuzzy();
        lane2_.SetName("lane2_");
        pn.PlaceList.add(lane2_);

        DataFuzzy p_tl = new DataFuzzy();
        p_tl.SetName("P_TL");
        p_tl.SetValue(greenSignal);
        pn.PlaceList.add(p_tl);

        DataTransfer p_out = new DataTransfer();
        p_out.SetName("P_out");
        p_out.Value = new TransferOperation("localhost", "1080", "P_i2");
        pn.PlaceList.add(p_out);

        DataFuzzy green = new DataFuzzy();
        green.SetName("Green");
        green.SetValue(greenSignal);
        pn.ConstantPlaceList.add(green);

        DataFuzzy p_o2 = new DataFuzzy();
        p_o2.SetName("P_o2");
        pn.PlaceList.add(p_o2);

        DataTransfer p_exit2 = new DataTransfer();
        p_exit2.SetName("P_Exit2");
        p_exit2.Value = new TransferOperation("localhost", "1085", "P_i1");
        pn.PlaceList.add(p_exit2);

        PetriTransition t_add = new PetriTransition(pn);
        t_add.TransitionName = "t_add";
        t_add.InputPlaceName.add("P_R");
        t_add.InputPlaceName.add("P_st");

        Condition tAddCt1 = new Condition(t_add, "P_R", TransitionCondition.NotNull);
        Condition tAddCt2 = new Condition(t_add, "P_st", TransitionCondition.NotNull);
        tAddCt1.SetNextCondition(LogicConnector.AND, tAddCt2);

        GuardMapping grdTadd = new GuardMapping();
        grdTadd.condition = tAddCt1;

        ArrayList<PlaceNameWithWeight> inputTadd = new ArrayList<>();
        inputTadd.add(new PlaceNameWithWeight("P_R", 1F));
        inputTadd.add(new PlaceNameWithWeight("P_st", 1F));

        ArrayList<String> outputTadd = new ArrayList<>();
        outputTadd.add("lane2");

        grdTadd.Activations.add(new Activation(t_add, flrsAdder, inputTadd, TransitionOperation.FLRS, outputTadd));
        t_add.GuardMappingList.add(grdTadd);
        t_add.Delay = 0;
        pn.Transitions.add(t_add);

        PetriTransition t_move = new PetriTransition(pn);
        t_move.TransitionName = "t_move";
        t_move.InputPlaceName.add("lane2");

        Condition tMoveCt = new Condition(t_move, "lane2", TransitionCondition.NotNull);
        GuardMapping grdTmove = new GuardMapping();
        grdTmove.condition = tMoveCt;

        ArrayList<PlaceNameWithWeight> inputTmove = new ArrayList<>();
        inputTmove.add(new PlaceNameWithWeight("lane2", 1F));
        ArrayList<String> outputTmove = new ArrayList<>();
        outputTmove.add("lane2_");

        grdTmove.Activations.add(new Activation(t_move, flrsMove, inputTmove, TransitionOperation.FLRS, outputTmove));
        t_move.GuardMappingList.add(grdTmove);
        t_move.Delay = 0;
        pn.Transitions.add(t_move);

        PetriTransition t_out = new PetriTransition(pn);
        t_out.TransitionName = "t_out";
        t_out.InputPlaceName.add("lane2_");
        t_out.InputPlaceName.add("P_TL");

        Condition toutCt1 = new Condition(t_out, "lane2_", TransitionCondition.NotNull);
        Condition toutCt2 = new Condition(t_out, "P_TL", TransitionCondition.Equal, "Green");
        toutCt1.SetNextCondition(LogicConnector.AND, toutCt2);

        GuardMapping grdTout = new GuardMapping();
        grdTout.condition = toutCt1;

        grdTout.Activations.add(new Activation(t_out, "lane2_", TransitionOperation.SendOverNetwork, "P_out"));
        grdTout.Activations.add(new Activation(t_out, "P_TL", TransitionOperation.Move, "P_TL"));

        t_out.GuardMappingList.add(grdTout);
        t_out.Delay = 0;
        pn.Transitions.add(t_out);

        PetriTransition t_1 = new PetriTransition(pn);
        t_1.TransitionName = "t_1";
        t_1.InputPlaceName.add("P_o2");

        Condition t1Ct1 = new Condition(t_1, "P_o2", TransitionCondition.NotNull);
        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = t1Ct1;
        grdT1.Activations.add(new Activation(t_1, "P_o2", TransitionOperation.SendOverNetwork, "P_Exit2"));

        t_1.GuardMappingList.add(grdT1);
        t_1.Delay = 0;
        pn.Transitions.add(t_1);

        System.out.println("Lane 2 Modified started \n ------------------------------");
        pn.Delay = 3000;
        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}