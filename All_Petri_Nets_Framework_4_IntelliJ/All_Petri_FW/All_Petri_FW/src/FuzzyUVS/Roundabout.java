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

public class Roundabout {
    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Roundabout";
        pn.NetworkPort = 1085;

        FLRS flrsMove = new FLRS(new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL));
        FLRS flrsAdder = new FLRS(
                new FV(FZ.NL), new FV(FZ.NL), new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR),
                new FV(FZ.NL), new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM),
                new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL),
                new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL), new FV(FZ.PL),
                new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL), new FV(FZ.PL), new FV(FZ.PL)
        );

        DataFuzzy p_i1 = new DataFuzzy();
        p_i1.SetName("P_i1");
        pn.PlaceList.add(p_i1);

        DataFuzzy p_i2 = new DataFuzzy();
        p_i2.SetName("P_i2");
        p_i2.SetValue(new Fuzzy(0.3F));
        pn.PlaceList.add(p_i2);

        DataFuzzy p_i3 = new DataFuzzy();
        p_i3.SetName("P_i3");
        p_i3.SetValue(new Fuzzy(0.3F));
        pn.PlaceList.add(p_i3);

        DataFuzzy l1 = new DataFuzzy();
        l1.SetName("L1");
        pn.PlaceList.add(l1);

        DataFuzzy l2 = new DataFuzzy();
        l2.SetName("L2");
        pn.PlaceList.add(l2);

        DataFuzzy l3 = new DataFuzzy();
        l3.SetName("L3");
        pn.PlaceList.add(l3);

        DataFuzzy l1_l2 = new DataFuzzy();
        l1_l2.SetName("L1_L2");
        pn.PlaceList.add(l1_l2);

        DataFuzzy l1l2l3 = new DataFuzzy();
        l1l2l3.SetName("L1L2L3");
        pn.PlaceList.add(l1l2l3);

        DataFuzzy e1 = new DataFuzzy();
        e1.SetName("E1");
        pn.PlaceList.add(e1);

        DataFuzzy e2 = new DataFuzzy();
        e2.SetName("E2");
        pn.PlaceList.add(e2);

        DataFuzzy e3 = new DataFuzzy();
        e3.SetName("E3");
        pn.PlaceList.add(e3);

        DataFuzzy p_out1 = new DataFuzzy();
        p_out1.SetName("P_out1");
        pn.PlaceList.add(p_out1);

        DataFuzzy p_out2 = new DataFuzzy();
        p_out2.SetName("P_out2");
        pn.PlaceList.add(p_out2);

        DataTransfer p_out3 = new DataTransfer();
        p_out3.SetName("P_out3");
        p_out3.Value = new TransferOperation("localhost", "1082", "P_R");
        pn.PlaceList.add(p_out3);

        DataFuzzy c033 = new DataFuzzy();
        c033.SetName("c033");
        c033.SetValue(new Fuzzy(0.33F));
        pn.ConstantPlaceList.add(c033);

        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("P_i1");
        Condition t1Ct = new Condition(t1, "P_i1", TransitionCondition.NotNull);
        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = t1Ct;
        ArrayList<PlaceNameWithWeight> in1 = new ArrayList<>();
        in1.add(new PlaceNameWithWeight("P_i1", 1F));
        ArrayList<String> out1 = new ArrayList<>();
        out1.add("L1");
        grdT1.Activations.add(new Activation(t1, flrsMove, in1, TransitionOperation.FLRS, out1));
        t1.GuardMappingList.add(grdT1);
        t1.Delay = 0;
        pn.Transitions.add(t1);

        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("P_i2");
        Condition t2Ct = new Condition(t2, "P_i2", TransitionCondition.NotNull);
        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = t2Ct;
        ArrayList<PlaceNameWithWeight> in2 = new ArrayList<>();
        in2.add(new PlaceNameWithWeight("P_i2", 1F));
        ArrayList<String> out2 = new ArrayList<>();
        out2.add("L2");
        grdT2.Activations.add(new Activation(t2, flrsMove, in2, TransitionOperation.FLRS, out2));
        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;
        pn.Transitions.add(t2);

        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("P_i3");
        Condition t3Ct = new Condition(t3, "P_i3", TransitionCondition.NotNull);
        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = t3Ct;
        ArrayList<PlaceNameWithWeight> in3 = new ArrayList<>();
        in3.add(new PlaceNameWithWeight("P_i3", 1F));
        ArrayList<String> out3 = new ArrayList<>();
        out3.add("L3");
        grdT3.Activations.add(new Activation(t3, flrsMove, in3, TransitionOperation.FLRS, out3));
        t3.GuardMappingList.add(grdT3);
        t3.Delay = 0;
        pn.Transitions.add(t3);

        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "t4";
        t4.InputPlaceName.add("L1");
        t4.InputPlaceName.add("L2");
        Condition t4Ct1 = new Condition(t4, "L1", TransitionCondition.NotNull);
        Condition t4Ct2 = new Condition(t4, "L2", TransitionCondition.NotNull);
        t4Ct1.SetNextCondition(LogicConnector.AND, t4Ct2);
        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = t4Ct1;
        ArrayList<PlaceNameWithWeight> in4 = new ArrayList<>();
        in4.add(new PlaceNameWithWeight("L1", 1F));
        in4.add(new PlaceNameWithWeight("L2", 1F));
        ArrayList<String> out4 = new ArrayList<>();
        out4.add("L1_L2");
        grdT4.Activations.add(new Activation(t4, flrsAdder, in4, TransitionOperation.FLRS, out4));
        t4.GuardMappingList.add(grdT4);
        t4.Delay = 0;
        pn.Transitions.add(t4);

        PetriTransition t_add2 = new PetriTransition(pn);
        t_add2.TransitionName = "t_add2";
        t_add2.InputPlaceName.add("L1_L2");
        t_add2.InputPlaceName.add("L3");
        Condition taCt1 = new Condition(t_add2, "L1_L2", TransitionCondition.NotNull);
        Condition taCt2 = new Condition(t_add2, "L3", TransitionCondition.NotNull);
        taCt1.SetNextCondition(LogicConnector.AND, taCt2);
        GuardMapping grdTa = new GuardMapping();
        grdTa.condition = taCt1;
        ArrayList<PlaceNameWithWeight> ina = new ArrayList<>();
        ina.add(new PlaceNameWithWeight("L1_L2", 1F));
        ina.add(new PlaceNameWithWeight("L3", 1F));
        ArrayList<String> outa = new ArrayList<>();
        outa.add("L1L2L3");
        grdTa.Activations.add(new Activation(t_add2, flrsAdder, ina, TransitionOperation.FLRS, outa));
        t_add2.GuardMappingList.add(grdTa);
        t_add2.Delay = 0;
        pn.Transitions.add(t_add2);

        PetriTransition T5 = new PetriTransition(pn);
        T5.TransitionName = "T5";
        T5.InputPlaceName.add("L1L2L3");
        Condition T5Ct = new Condition(T5, "L1L2L3", TransitionCondition.NotNull);
        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct;

        ArrayList<PlaceNameWithWeight> split = new ArrayList<>();
        split.add(new PlaceNameWithWeight("L1L2L3", 1F));
        split.add(new PlaceNameWithWeight("c033", 1F));

        grdT5.Activations.add(new Activation(split, TransitionOperation.Prod_Fuzzy, "E1", T5));
        grdT5.Activations.add(new Activation(split, TransitionOperation.Prod_Fuzzy, "E2", T5));
        grdT5.Activations.add(new Activation(split, TransitionOperation.Prod_Fuzzy, "E3", T5));

        T5.GuardMappingList.add(grdT5);
        T5.Delay = 0;
        pn.Transitions.add(T5);

        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "t5";
        t5.InputPlaceName.add("E1");
        Condition t5Ct = new Condition(t5, "E1", TransitionCondition.NotNull);
        GuardMapping grdT5_move = new GuardMapping();
        grdT5_move.condition = t5Ct;
        ArrayList<PlaceNameWithWeight> in5 = new ArrayList<>();
        in5.add(new PlaceNameWithWeight("E1", 1F));
        ArrayList<String> out5 = new ArrayList<>();
        out5.add("P_out1");
        grdT5_move.Activations.add(new Activation(t5, flrsMove, in5, TransitionOperation.FLRS, out5));
        t5.GuardMappingList.add(grdT5_move);
        t5.Delay = 0;
        pn.Transitions.add(t5);

        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "t6";
        t6.InputPlaceName.add("E2");
        Condition t6Ct = new Condition(t6, "E2", TransitionCondition.NotNull);
        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = t6Ct;
        ArrayList<PlaceNameWithWeight> in6 = new ArrayList<>();
        in6.add(new PlaceNameWithWeight("E2", 1F));
        ArrayList<String> out6 = new ArrayList<>();
        out6.add("P_out2");
        grdT6.Activations.add(new Activation(t6, flrsMove, in6, TransitionOperation.FLRS, out6));
        t6.GuardMappingList.add(grdT6);
        t6.Delay = 0;
        pn.Transitions.add(t6);

        PetriTransition t7 = new PetriTransition(pn);
        t7.TransitionName = "t7";
        t7.InputPlaceName.add("E3");
        Condition t7Ct = new Condition(t7, "E3", TransitionCondition.NotNull);
        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = t7Ct;
        grdT7.Activations.add(new Activation(t7, "E3", TransitionOperation.SendOverNetwork, "P_out3"));
        t7.GuardMappingList.add(grdT7);
        t7.Delay = 0;
        pn.Transitions.add(t7);

        System.out.println("Roundabout started \n ------------------------------");
        pn.Delay = 3000;
        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}