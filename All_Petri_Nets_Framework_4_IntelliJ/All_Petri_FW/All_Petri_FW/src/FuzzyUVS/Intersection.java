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

public class Intersection {
    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Intersection";
        pn.NetworkPort = 1080;

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
        pn.PlaceList.add(p_i2);

        DataFuzzy p_i3 = new DataFuzzy();
        p_i3.SetName("P_i3");
        pn.PlaceList.add(p_i3);

        DataFuzzy p_i4 = new DataFuzzy();
        p_i4.SetName("P_i4");
        pn.PlaceList.add(p_i4);

        DataFuzzy p_a1 = new DataFuzzy();
        p_a1.SetName("P_a1");
        pn.PlaceList.add(p_a1);

        DataFuzzy p_a2 = new DataFuzzy();
        p_a2.SetName("P_a2");
        pn.PlaceList.add(p_a2);

        DataFuzzy p_i = new DataFuzzy();
        p_i.SetName("P_i");
        pn.PlaceList.add(p_i);

        DataFuzzy p_o1 = new DataFuzzy();
        p_o1.SetName("P_o1");
        pn.PlaceList.add(p_o1);

        DataFuzzy p_o2 = new DataFuzzy();
        p_o2.SetName("P_o2");
        pn.PlaceList.add(p_o2);

        DataFuzzy p_o3 = new DataFuzzy();
        p_o3.SetName("P_o3");
        pn.PlaceList.add(p_o3);

        DataFuzzy p_o4 = new DataFuzzy();
        p_o4.SetName("P_o4");
        pn.PlaceList.add(p_o4);

        DataTransfer p_out1 = new DataTransfer();
        p_out1.SetName("P_out1");
        p_out1.Value = new TransferOperation("localhost", "1081", "P_o1");
        pn.PlaceList.add(p_out1);

        DataTransfer p_out2 = new DataTransfer();
        p_out2.SetName("P_out2");
        p_out2.Value = new TransferOperation("localhost", "1082", "P_o2");
        pn.PlaceList.add(p_out2);

        DataTransfer p_out3 = new DataTransfer();
        p_out3.SetName("P_out3");
        p_out3.Value = new TransferOperation("localhost", "1083", "P_o3");
        pn.PlaceList.add(p_out3);

        DataTransfer p_out4 = new DataTransfer();
        p_out4.SetName("P_out4");
        p_out4.Value = new TransferOperation("localhost", "1084", "P_o4");
        pn.PlaceList.add(p_out4);

        DataFuzzy c025 = new DataFuzzy();
        c025.SetName("c025");
        c025.SetValue(new Fuzzy(0.25F));
        pn.ConstantPlaceList.add(c025);

        DataFuzzy c050 = new DataFuzzy();
        c050.SetName("c050");
        c050.SetValue(new Fuzzy(0.5F));
        pn.ConstantPlaceList.add(c050);

        DataFuzzy c012 = new DataFuzzy();
        c012.SetName("c012");
        c012.SetValue(new Fuzzy(0.12F));
        pn.ConstantPlaceList.add(c012);

        DataFuzzy c013 = new DataFuzzy();
        c013.SetName("c013");
        c013.SetValue(new Fuzzy(0.13F));
        pn.ConstantPlaceList.add(c013);

        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("P_i1");
        t1.InputPlaceName.add("P_i2");

        Condition t1Ct1 = new Condition(t1, "P_i1", TransitionCondition.NotNull);
        Condition t1Ct2 = new Condition(t1, "P_i2", TransitionCondition.NotNull);
        t1Ct1.SetNextCondition(LogicConnector.AND, t1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = t1Ct1;

        ArrayList<PlaceNameWithWeight> inputT1 = new ArrayList<>();
        inputT1.add(new PlaceNameWithWeight("P_i1", 1F));
        inputT1.add(new PlaceNameWithWeight("P_i2", 1F));

        ArrayList<String> outputT1 = new ArrayList<>();
        outputT1.add("P_a1");

        grdT1.Activations.add(new Activation(t1, flrsAdder, inputT1, TransitionOperation.FLRS, outputT1));
        t1.GuardMappingList.add(grdT1);
        t1.Delay = 0;
        pn.Transitions.add(t1);

        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("P_i3");
        t2.InputPlaceName.add("P_i4");

        Condition t2Ct1 = new Condition(t2, "P_i3", TransitionCondition.NotNull);
        Condition t2Ct2 = new Condition(t2, "P_i4", TransitionCondition.NotNull);
        t2Ct1.SetNextCondition(LogicConnector.AND, t2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = t2Ct1;

        ArrayList<PlaceNameWithWeight> inputT2 = new ArrayList<>();
        inputT2.add(new PlaceNameWithWeight("P_i3", 1F));
        inputT2.add(new PlaceNameWithWeight("P_i4", 1F));

        ArrayList<String> outputT2 = new ArrayList<>();
        outputT2.add("P_a2");

        grdT2.Activations.add(new Activation(t2, flrsAdder, inputT2, TransitionOperation.FLRS, outputT2));
        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;
        pn.Transitions.add(t2);

        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("P_a1");
        t3.InputPlaceName.add("P_a2");

        Condition t3Ct1 = new Condition(t3, "P_a1", TransitionCondition.NotNull);
        Condition t3Ct2 = new Condition(t3, "P_a2", TransitionCondition.NotNull);
        t3Ct1.SetNextCondition(LogicConnector.AND, t3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = t3Ct1;

        ArrayList<PlaceNameWithWeight> inputT3 = new ArrayList<>();
        inputT3.add(new PlaceNameWithWeight("P_a1", 1F));
        inputT3.add(new PlaceNameWithWeight("P_a2", 1F));

        ArrayList<String> outputT3 = new ArrayList<>();
        outputT3.add("P_i");

        grdT3.Activations.add(new Activation(t3, flrsAdder, inputT3, TransitionOperation.FLRS, outputT3));
        t3.GuardMappingList.add(grdT3);
        t3.Delay = 0;
        pn.Transitions.add(t3);

        PetriTransition t_s = new PetriTransition(pn);
        t_s.TransitionName = "t_s";
        t_s.InputPlaceName.add("P_i");

        Condition tsCt1 = new Condition(t_s, "P_i", TransitionCondition.NotNull);
        GuardMapping grdTs = new GuardMapping();
        grdTs.condition = tsCt1;

        ArrayList<PlaceNameWithWeight> split1 = new ArrayList<>();
        split1.add(new PlaceNameWithWeight("P_i", 1F));
        split1.add(new PlaceNameWithWeight("c025", 1F));

        ArrayList<PlaceNameWithWeight> split2 = new ArrayList<>();
        split2.add(new PlaceNameWithWeight("P_i", 1F));
        split2.add(new PlaceNameWithWeight("c050", 1F));

        ArrayList<PlaceNameWithWeight> split3 = new ArrayList<>();
        split3.add(new PlaceNameWithWeight("P_i", 1F));
        split3.add(new PlaceNameWithWeight("c012", 1F));

        ArrayList<PlaceNameWithWeight> split4 = new ArrayList<>();
        split4.add(new PlaceNameWithWeight("P_i", 1F));
        split4.add(new PlaceNameWithWeight("c013", 1F));

        grdTs.Activations.add(new Activation(split1, TransitionOperation.Prod_Fuzzy, "P_o1", t_s));
        grdTs.Activations.add(new Activation(split2, TransitionOperation.Prod_Fuzzy, "P_o2", t_s));
        grdTs.Activations.add(new Activation(split3, TransitionOperation.Prod_Fuzzy, "P_o3", t_s));
        grdTs.Activations.add(new Activation(split4, TransitionOperation.Prod_Fuzzy, "P_o4", t_s));

        t_s.GuardMappingList.add(grdTs);
        t_s.Delay = 0;
        pn.Transitions.add(t_s);

        PetriTransition t_out1 = new PetriTransition(pn);
        t_out1.TransitionName = "t_out1";
        t_out1.InputPlaceName.add("P_o1");
        Condition tout1Ct = new Condition(t_out1, "P_o1", TransitionCondition.NotNull);
        GuardMapping grdTout1 = new GuardMapping();
        grdTout1.condition = tout1Ct;
        grdTout1.Activations.add(new Activation(t_out1, "P_o1", TransitionOperation.SendOverNetwork, "P_out1"));
        t_out1.GuardMappingList.add(grdTout1);
        t_out1.Delay = 0;
        pn.Transitions.add(t_out1);

        PetriTransition t_out2 = new PetriTransition(pn);
        t_out2.TransitionName = "t_out2";
        t_out2.InputPlaceName.add("P_o2");
        Condition tout2Ct = new Condition(t_out2, "P_o2", TransitionCondition.NotNull);
        GuardMapping grdTout2 = new GuardMapping();
        grdTout2.condition = tout2Ct;
        grdTout2.Activations.add(new Activation(t_out2, "P_o2", TransitionOperation.SendOverNetwork, "P_out2"));
        t_out2.GuardMappingList.add(grdTout2);
        t_out2.Delay = 0;
        pn.Transitions.add(t_out2);

        PetriTransition t_out3 = new PetriTransition(pn);
        t_out3.TransitionName = "t_out3";
        t_out3.InputPlaceName.add("P_o3");
        Condition tout3Ct = new Condition(t_out3, "P_o3", TransitionCondition.NotNull);
        GuardMapping grdTout3 = new GuardMapping();
        grdTout3.condition = tout3Ct;
        grdTout3.Activations.add(new Activation(t_out3, "P_o3", TransitionOperation.SendOverNetwork, "P_out3"));
        t_out3.GuardMappingList.add(grdTout3);
        t_out3.Delay = 0;
        pn.Transitions.add(t_out3);

        PetriTransition t_out4 = new PetriTransition(pn);
        t_out4.TransitionName = "t_out4";
        t_out4.InputPlaceName.add("P_o4");
        Condition tout4Ct = new Condition(t_out4, "P_o4", TransitionCondition.NotNull);
        GuardMapping grdTout4 = new GuardMapping();
        grdTout4.condition = tout4Ct;
        grdTout4.Activations.add(new Activation(t_out4, "P_o4", TransitionOperation.SendOverNetwork, "P_out4"));
        t_out4.GuardMappingList.add(grdTout4);
        t_out4.Delay = 0;
        pn.Transitions.add(t_out4);

        System.out.println("Intersection started \n ------------------------------");
        pn.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}