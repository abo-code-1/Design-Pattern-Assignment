package galactic.demo;

import galactic.bodies.Planet;
import galactic.bodies.SpaceStation;
import galactic.cargo.Cargo;
import galactic.dispatcher.Dispatcher;
import galactic.dispatcher.Result;
import galactic.drones.HeavyDrone;
import galactic.drones.LightDrone;
import galactic.task.DeliveryTask;

public class DemoApp {

    public static void main(String[] args) {
        Planet earth = new Planet("Earth", 0, 0, "Nitrogen-Oxygen");
        SpaceStation orbital = new SpaceStation("Mars Orbital", 100, 0, 3);

        Cargo box = new Cargo(50, "Heavy Equipment");

        LightDrone light = new LightDrone("LD-01", 20);
        HeavyDrone heavy = new HeavyDrone("HD-01", 100);

        DeliveryTask task = new DeliveryTask(earth, orbital, box);
        Dispatcher dispatcher = new Dispatcher();

        System.out.println("Assign to LightDrone");
        Result r1 = dispatcher.assignTask(task, light);
        System.out.println("Result: " + r1.ok() + ", Reason: " + r1.reason());

        System.out.println();
        System.out.println("Assign to HeavyDrone");
        Result r2 = dispatcher.assignTask(task, heavy);
        System.out.println("Result: " + r2.ok());
        System.out.println("Task state: " + task.getState());
        System.out.println("Drone status: " + heavy.getStatus());

        System.out.println();
        System.out.println("Estimated Time");
        System.out.println("Time: " + task.estimateTime() + " minutes");

        System.out.println();
        System.out.println("Complete Task");
        Result r3 = dispatcher.completeTask(task);
        System.out.println("Result: " + r3.ok());
        System.out.println("Final task state: " + task.getState());
        System.out.println("Final drone status: " + heavy.getStatus());
    }
}
