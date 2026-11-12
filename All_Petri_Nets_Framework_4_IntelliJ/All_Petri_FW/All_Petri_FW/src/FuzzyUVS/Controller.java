package FuzzyUVS;

import java.util.ArrayList;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFuzzy;
import DataObjects.DataInteger;
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

public class Controller {

    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Controller";
        pn.NetworkPort = 1087;

        DataFuzzy red = new DataFuzzy();
        red.SetName("Red");
        red.SetValue(new Fuzzy(-1.0F));
        pn.ConstantPlaceList.add(red);

        DataFuzzy green = new DataFuzzy();
        green.SetName("Green");
        green.SetValue(new Fuzzy(0.0F));
        pn.ConstantPlaceList.add(green);

        DataFuzzy yellow = new DataFuzzy();
        yellow.SetName("Yellow");
        yellow.SetValue(new Fuzzy(1.0F));
        pn.ConstantPlaceList.add(yellow);

        DataFuzzy ini = new DataFuzzy();
        ini.SetName("ini");
        pn.PlaceList.add(ini);

        DataFuzzy r1r2r3r4 = new DataFuzzy();
        r1r2r3r4.SetName("r1r2r3r4");
        pn.PlaceList.add(r1r2r3r4);

        DataFuzzy g1r2r3r4 = new DataFuzzy();
        g1r2r3r4.SetName("g1r2r3r4");
        pn.PlaceList.add(g1r2r3r4);

        DataFuzzy y1r2r3r4 = new DataFuzzy();
        y1r2r3r4.SetName("y1r2r3r4");
        pn.PlaceList.add(y1r2r3r4);

        DataFuzzy r1g2r3r4 = new DataFuzzy();
        r1g2r3r4.SetName("r1g2r3r4");
        pn.PlaceList.add(r1g2r3r4);

        DataFuzzy r1y2r3r4 = new DataFuzzy();
        r1y2r3r4.SetName("r1y2r3r4");
        pn.PlaceList.add(r1y2r3r4);

        DataFuzzy r1r2g3r4 = new DataFuzzy();
        r1r2g3r4.SetName("r1r2g3r4");
        pn.PlaceList.add(r1r2g3r4);

        DataFuzzy r1r2y3r4 = new DataFuzzy();
        r1r2y3r4.SetName("r1r2y3r4");
        pn.PlaceList.add(r1r2y3r4);

        DataFuzzy r1r2r3g4 = new DataFuzzy();
        r1r2r3g4.SetName("r1r2r3g4");
        pn.PlaceList.add(r1r2r3g4);

        DataFuzzy r1r2r3y4 = new DataFuzzy();
        r1r2r3y4.SetName("r1r2r3y4");
        pn.PlaceList.add(r1r2r3y4);

        DataInteger d1 = new DataInteger();
        d1.SetName("d1");
        d1.SetValue(5);
        pn.PlaceList.add(d1);

        DataInteger d2 = new DataInteger();
        d2.SetName("d2");
        d2.SetValue(5);
        pn.PlaceList.add(d2);

        DataInteger d3 = new DataInteger();
        d3.SetName("d3");
        d3.SetValue(5);
        pn.PlaceList.add(d3);

        DataInteger d4 = new DataInteger();
        d4.SetName("d4");
        d4.SetValue(5);
        pn.PlaceList.add(d4);

        DataTransfer p_tl1 = new DataTransfer();
        p_tl1.SetName("P_TL1");
        p_tl1.Value = new TransferOperation("localhost", "1081", "P_TL");
        pn.PlaceList.add(p_tl1);

        DataTransfer p_tl2 = new DataTransfer();
        p_tl2.SetName("P_TL2");
        p_tl2.Value = new TransferOperation("localhost", "1082", "P_TL");
        pn.PlaceList.add(p_tl2);

        DataTransfer p_tl3 = new DataTransfer();
        p_tl3.SetName("P_TL3");
        p_tl3.Value = new TransferOperation("localhost", "1083", "P_TL");
        pn.PlaceList.add(p_tl3);

        DataTransfer p_tl4 = new DataTransfer();
        p_tl4.SetName("P_TL4");
        p_tl4.Value = new TransferOperation("localhost", "1084", "P_TL");
        pn.PlaceList.add(p_tl4);

        FLRS flrsMove = new FLRS(new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL));


        PetriTransition t_ini = new PetriTransition(pn);
        t_ini.TransitionName = "t_ini";
        t_ini.InputPlaceName.add("ini");

        Condition tIniCt = new Condition(t_ini, "ini", TransitionCondition.NotNull);
        GuardMapping grdTIni = new GuardMapping();
        grdTIni.condition = tIniCt;

        ArrayList<PlaceNameWithWeight> inIni = new ArrayList<>();
        inIni.add(new PlaceNameWithWeight("ini", 1F));
        ArrayList<String> outIni = new ArrayList<>();
        outIni.add("r1r2r3r4");

        grdTIni.Activations.add(new Activation(t_ini, flrsMove, inIni, TransitionOperation.FLRS, outIni));
        grdTIni.Activations.add(new Activation(t_ini, "Red", TransitionOperation.SendOverNetwork, "P_TL1"));
        grdTIni.Activations.add(new Activation(t_ini, "Red", TransitionOperation.SendOverNetwork, "P_TL2"));
        grdTIni.Activations.add(new Activation(t_ini, "Red", TransitionOperation.SendOverNetwork, "P_TL3"));
        grdTIni.Activations.add(new Activation(t_ini, "Red", TransitionOperation.SendOverNetwork, "P_TL4"));
        t_ini.GuardMappingList.add(grdTIni);
        t_ini.Delay = 0;
        pn.Transitions.add(t_ini);

        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("r1r2r3r4");
        Condition t1Ct = new Condition(t1, "r1r2r3r4", TransitionCondition.NotNull);
        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = t1Ct;
        ArrayList<PlaceNameWithWeight> inT1 = new ArrayList<>();
        inT1.add(new PlaceNameWithWeight("r1r2r3r4", 1F));
        ArrayList<String> outT1 = new ArrayList<>();
        outT1.add("g1r2r3r4");
        grdT1.Activations.add(new Activation(t1, flrsMove, inT1, TransitionOperation.FLRS, outT1));
        grdT1.Activations.add(new Activation(t1, "Green", TransitionOperation.SendOverNetwork, "P_TL1"));
        t1.GuardMappingList.add(grdT1);
        t1.Delay = 0;
        pn.Transitions.add(t1);

        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("g1r2r3r4");
        t2.InputPlaceName.add("d1");
        Condition t2Ct1 = new Condition(t2, "g1r2r3r4", TransitionCondition.NotNull);
        Condition t2Ct2 = new Condition(t2, "d1", TransitionCondition.NotNull);
        t2Ct1.SetNextCondition(LogicConnector.AND, t2Ct2);
        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = t2Ct1;
        ArrayList<PlaceNameWithWeight> inT2 = new ArrayList<>();
        inT2.add(new PlaceNameWithWeight("g1r2r3r4", 1F));
        ArrayList<String> outT2 = new ArrayList<>();
        outT2.add("y1r2r3r4");
        grdT2.Activations.add(new Activation(t2, flrsMove, inT2, TransitionOperation.FLRS, outT2));
        grdT2.Activations.add(new Activation(t2, "d1", TransitionOperation.Move, "d1"));
        grdT2.Activations.add(new Activation(t2, "Yellow", TransitionOperation.SendOverNetwork, "P_TL1"));
        grdT2.Activations.add(new Activation(t2, "d1", TransitionOperation.DynamicDelay, ""));
        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;
        pn.Transitions.add(t2);

        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("y1r2r3r4");
        Condition t3Ct = new Condition(t3, "y1r2r3r4", TransitionCondition.NotNull);
        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = t3Ct;
        ArrayList<PlaceNameWithWeight> inT3 = new ArrayList<>();
        inT3.add(new PlaceNameWithWeight("y1r2r3r4", 1F));
        ArrayList<String> outT3 = new ArrayList<>();
        outT3.add("r1g2r3r4");
        grdT3.Activations.add(new Activation(t3, flrsMove, inT3, TransitionOperation.FLRS, outT3));
        grdT3.Activations.add(new Activation(t3, "Red", TransitionOperation.SendOverNetwork, "P_TL1"));
        grdT3.Activations.add(new Activation(t3, "Green", TransitionOperation.SendOverNetwork, "P_TL2"));
        t3.GuardMappingList.add(grdT3);
        t3.Delay = 5;
        pn.Transitions.add(t3);

        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "t4";
        t4.InputPlaceName.add("r1g2r3r4");
        t4.InputPlaceName.add("d2");
        Condition t4Ct1 = new Condition(t4, "r1g2r3r4", TransitionCondition.NotNull);
        Condition t4Ct2 = new Condition(t4, "d2", TransitionCondition.NotNull);
        t4Ct1.SetNextCondition(LogicConnector.AND, t4Ct2);
        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = t4Ct1;
        ArrayList<PlaceNameWithWeight> inT4 = new ArrayList<>();
        inT4.add(new PlaceNameWithWeight("r1g2r3r4", 1F));
        ArrayList<String> outT4 = new ArrayList<>();
        outT4.add("r1y2r3r4");
        grdT4.Activations.add(new Activation(t4, flrsMove, inT4, TransitionOperation.FLRS, outT4));
        grdT4.Activations.add(new Activation(t4, "d2", TransitionOperation.Move, "d2"));
        grdT4.Activations.add(new Activation(t4, "Yellow", TransitionOperation.SendOverNetwork, "P_TL2"));
        grdT4.Activations.add(new Activation(t4, "d2", TransitionOperation.DynamicDelay, ""));
        t4.GuardMappingList.add(grdT4);
        t4.Delay = 0;
        pn.Transitions.add(t4);

        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "t5";
        t5.InputPlaceName.add("r1y2r3r4");
        Condition t5Ct = new Condition(t5, "r1y2r3r4", TransitionCondition.NotNull);
        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = t5Ct;
        ArrayList<PlaceNameWithWeight> inT5 = new ArrayList<>();
        inT5.add(new PlaceNameWithWeight("r1y2r3r4", 1F));
        ArrayList<String> outT5 = new ArrayList<>();
        outT5.add("r1r2g3r4");
        grdT5.Activations.add(new Activation(t5, flrsMove, inT5, TransitionOperation.FLRS, outT5));
        grdT5.Activations.add(new Activation(t5, "Red", TransitionOperation.SendOverNetwork, "P_TL2"));
        grdT5.Activations.add(new Activation(t5, "Green", TransitionOperation.SendOverNetwork, "P_TL3"));
        t5.GuardMappingList.add(grdT5);
        t5.Delay = 5;
        pn.Transitions.add(t5);

        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "t6";
        t6.InputPlaceName.add("r1r2g3r4");
        t6.InputPlaceName.add("d3");
        Condition t6Ct1 = new Condition(t6, "r1r2g3r4", TransitionCondition.NotNull);
        Condition t6Ct2 = new Condition(t6, "d3", TransitionCondition.NotNull);
        t6Ct1.SetNextCondition(LogicConnector.AND, t6Ct2);
        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = t6Ct1;
        ArrayList<PlaceNameWithWeight> inT6 = new ArrayList<>();
        inT6.add(new PlaceNameWithWeight("r1r2g3r4", 1F));
        ArrayList<String> outT6 = new ArrayList<>();
        outT6.add("r1r2y3r4");
        grdT6.Activations.add(new Activation(t6, flrsMove, inT6, TransitionOperation.FLRS, outT6));
        grdT6.Activations.add(new Activation(t6, "d3", TransitionOperation.Move, "d3"));
        grdT6.Activations.add(new Activation(t6, "Yellow", TransitionOperation.SendOverNetwork, "P_TL3"));
        grdT6.Activations.add(new Activation(t6, "d3", TransitionOperation.DynamicDelay, ""));
        t6.GuardMappingList.add(grdT6);
        t6.Delay = 0;
        pn.Transitions.add(t6);

        PetriTransition t7 = new PetriTransition(pn);
        t7.TransitionName = "t7";
        t7.InputPlaceName.add("r1r2y3r4");
        Condition t7Ct = new Condition(t7, "r1r2y3r4", TransitionCondition.NotNull);
        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = t7Ct;
        ArrayList<PlaceNameWithWeight> inT7 = new ArrayList<>();
        inT7.add(new PlaceNameWithWeight("r1r2y3r4", 1F));
        ArrayList<String> outT7 = new ArrayList<>();
        outT7.add("r1r2r3g4");
        grdT7.Activations.add(new Activation(t7, flrsMove, inT7, TransitionOperation.FLRS, outT7));
        grdT7.Activations.add(new Activation(t7, "Red", TransitionOperation.SendOverNetwork, "P_TL3"));
        grdT7.Activations.add(new Activation(t7, "Green", TransitionOperation.SendOverNetwork, "P_TL4"));
        t7.GuardMappingList.add(grdT7);
        t7.Delay = 5;
        pn.Transitions.add(t7);

        PetriTransition t8 = new PetriTransition(pn);
        t8.TransitionName = "t8";
        t8.InputPlaceName.add("r1r2r3g4");
        t8.InputPlaceName.add("d4");
        Condition t8Ct1 = new Condition(t8, "r1r2r3g4", TransitionCondition.NotNull);
        Condition t8Ct2 = new Condition(t8, "d4", TransitionCondition.NotNull);
        t8Ct1.SetNextCondition(LogicConnector.AND, t8Ct2);
        GuardMapping grdT8 = new GuardMapping();
        grdT8.condition = t8Ct1;
        ArrayList<PlaceNameWithWeight> inT8 = new ArrayList<>();
        inT8.add(new PlaceNameWithWeight("r1r2r3g4", 1F));
        ArrayList<String> outT8 = new ArrayList<>();
        outT8.add("r1r2r3y4");
        grdT8.Activations.add(new Activation(t8, flrsMove, inT8, TransitionOperation.FLRS, outT8));
        grdT8.Activations.add(new Activation(t8, "d4", TransitionOperation.Move, "d4"));
        grdT8.Activations.add(new Activation(t8, "Yellow", TransitionOperation.SendOverNetwork, "P_TL4"));
        grdT8.Activations.add(new Activation(t8, "d4", TransitionOperation.DynamicDelay, ""));
        t8.GuardMappingList.add(grdT8);
        t8.Delay = 0;
        pn.Transitions.add(t8);

        PetriTransition t9 = new PetriTransition(pn);
        t9.TransitionName = "t9";
        t9.InputPlaceName.add("r1r2r3y4");
        Condition t9Ct = new Condition(t9, "r1r2r3y4", TransitionCondition.NotNull);
        GuardMapping grdT9 = new GuardMapping();
        grdT9.condition = t9Ct;
        ArrayList<PlaceNameWithWeight> inT9 = new ArrayList<>();
        inT9.add(new PlaceNameWithWeight("r1r2r3y4", 1F));
        ArrayList<String> outT9 = new ArrayList<>();
        outT9.add("r1r2r3r4");
        grdT9.Activations.add(new Activation(t9, flrsMove, inT9, TransitionOperation.FLRS, outT9));
        grdT9.Activations.add(new Activation(t9, "Red", TransitionOperation.SendOverNetwork, "P_TL4"));
        t9.GuardMappingList.add(grdT9);
        t9.Delay = 5;
        pn.Transitions.add(t9);

        System.out.println("Controller started \n ------------------------------");
        pn.Delay = 3000;
        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}