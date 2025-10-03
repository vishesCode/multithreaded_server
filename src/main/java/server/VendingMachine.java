package server;

interface MachineState {
    void insertCoin(Machine machine);

    void selectItem(Machine machine);

    void dispense(Machine machine);

    void refundCoin(Machine machine);
}

class NoCoinState implements MachineState {
    @Override
    public void insertCoin(Machine machine) {
        System.out.println("Insert Coin");
        machine.setMachineState(new HasCoinState());
    }

    @Override
    public void selectItem(Machine machine) {
        throw new IllegalStateException("No Coin");
    }

    @Override
    public void dispense(Machine machine) {
        throw new IllegalStateException("No Coin");
    }

    @Override
    public void refundCoin(Machine machine) {
        throw new IllegalStateException("No Coin");
    }
}

class HasCoinState implements MachineState {
    @Override
    public void insertCoin(Machine machine) {
        throw new IllegalStateException("Coin Exists");
    }

    @Override
    public void selectItem(Machine machine) {
        System.out.println("Select Item");
        machine.setMachineState(new DispensingState());
    }

    @Override
    public void dispense(Machine machine) {
        throw new IllegalStateException("Item not selected");
    }

    @Override
    public void refundCoin(Machine machine) {
        System.out.println("Refund Coin");
        machine.setMachineState(new NoCoinState());
    }
}

class DispensingState implements MachineState {
    @Override
    public void insertCoin(Machine machine) {
        throw new IllegalStateException("Coin Exists");
    }

    @Override
    public void selectItem(Machine machine) {
        throw new IllegalStateException("Item Selected");
    }

    @Override
    public void dispense(Machine machine) {
        if(machine.getStock() < 0) {
            machine.setMachineState(new SoldOutState());
        } else {
            machine.decrementStock();
            machine.setMachineState(new NoCoinState());
        }
    }

    @Override
    public void refundCoin(Machine machine) {
        throw new IllegalStateException("Can't Refund Coin");
    }
}

class SoldOutState implements MachineState {
    @Override
    public void insertCoin(Machine machine) {
        throw new IllegalStateException("Sold Out");
    }

    @Override
    public void selectItem(Machine machine) {
        throw new IllegalStateException("Sold Out");
    }

    @Override
    public void dispense(Machine machine) {
        throw new IllegalStateException("Sold Out");
    }

    @Override
    public void refundCoin(Machine machine) {
        throw new IllegalStateException("Sold Out");
    }
}

class Machine {
    private MachineState machineState;
    private int stock;

    Machine() {
        this.machineState = new NoCoinState();
    }

    public int getStock() {
        return stock;
    }

    public void decrementStock() {
        this.stock -= 1;
    }

    public void decrementStock(int stock) {
        this.stock -= stock;
    }

    public void setMachineState(MachineState machineState) {
        this.machineState = machineState;
    }

    public void insertCoin(Machine machine) {
        this.machineState.insertCoin(machine);
    }

    public void selectItem(Machine machine) {
        this.machineState.selectItem(machine);
    }

    public void dispense(Machine machine) {
        this.machineState.dispense(machine);
    }

    public void refundCoin(Machine machine) {
        this.machineState.refundCoin(machine);
    }
}

public class VendingMachine {
    public static void main(String[] args) {
        
    }
}
