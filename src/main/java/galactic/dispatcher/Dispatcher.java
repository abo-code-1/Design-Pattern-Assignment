package galactic.dispatcher;

import galactic.drones.Drone;
import galactic.drones.DroneStatus;
import galactic.task.DeliveryTask;
import galactic.task.TaskState;

import java.lang.reflect.Method;

public class Dispatcher {

    public Result assignTask(DeliveryTask task, Drone drone) {
        if (task == null || drone == null) {
            return new Result(false, "Task or drone is null");
        }
        if (drone.getStatus() != DroneStatus.IDLE) {
            return new Result(false, "Drone is not idle");
        }
        if (task.getCargo().getWeightKg() > drone.getMaxPayloadKg()) {
            return new Result(false, "Cargo too heavy for this drone");
        }
        if (task.getState() != TaskState.CREATED) {
            return new Result(false, "Task is not in CREATED state");
        }

        try {
            Method setState = DeliveryTask.class.getDeclaredMethod("setState", TaskState.class);
            setState.setAccessible(true);
            setState.invoke(task, TaskState.ASSIGNED);

            Method setDrone = DeliveryTask.class.getDeclaredMethod("setAssignedDrone", Drone.class);
            setDrone.setAccessible(true);
            setDrone.invoke(task, drone);

            Method setStatus = Drone.class.getDeclaredMethod("setStatus", DroneStatus.class);
            setStatus.setAccessible(true);
            setStatus.invoke(drone, DroneStatus.IN_FLIGHT);
        } catch (Exception e) {
            return new Result(false, "Internal error: " + e.getMessage());
        }

        return new Result(true, "");
    }

    public Result completeTask(DeliveryTask task) {
        if (task == null) {
            return new Result(false, "Task is null");
        }
        if (task.getState() != TaskState.ASSIGNED) {
            return new Result(false, "Task is not in ASSIGNED state");
        }
        if (task.getAssignedDrone() == null) {
            return new Result(false, "No drone assigned to task");
        }
        if (task.getAssignedDrone().getStatus() != DroneStatus.IN_FLIGHT) {
            return new Result(false, "Drone is not in flight");
        }

        Drone drn = task.getAssignedDrone();

        try {
            Method setState = DeliveryTask.class.getDeclaredMethod("setState", TaskState.class);
            setState.setAccessible(true);
            setState.invoke(task, TaskState.DONE);

            Method setStatus = Drone.class.getDeclaredMethod("setStatus", DroneStatus.class);
            setStatus.setAccessible(true);
            setStatus.invoke(drn, DroneStatus.IDLE);
        } catch (Exception e) {
            return new Result(false, "Internal error: " + e.getMessage());
        }

        return new Result(true, "");
    }
}
