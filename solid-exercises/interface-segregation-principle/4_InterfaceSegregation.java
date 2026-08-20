// ===============================================
// I - INTERFACE SEGREGATION PRINCIPLE
// ===============================================
// Muchas interfaces específicas > Una interfaz gorda

// ❌ SIN APLICAR - Una interfaz que hace de todo (GORDA)
interface WorkerBad {
    void work();
    void eat();
    void sleep();
    void drive();
    void manageTeam();
}

class RobotBad implements WorkerBad {
    @Override
    public void work() {
        System.out.println("🤖 Robot trabajando...");
    }
    
    @Override
    public void eat() {
        // PROBLEMA: ¡Los robots no comen!
        throw new UnsupportedOperationException("Los robots no comen");
    }
    
    @Override
    public void sleep() {
        // PROBLEMA: ¡Los robots no duermen!
        throw new UnsupportedOperationException("Los robots no duermen");
    }
    
    @Override
    public void drive() {
        // Algunos robots sí pueden conducir, pero no todos
        System.out.println("🤖 Robot conduciendo...");
    }
    
    @Override
    public void manageTeam() {
        // PROBLEMA: ¡Los robots no gerencian!
        throw new UnsupportedOperationException("Los robots no gestionan equipos");
    }
}

class HumanBad implements WorkerBad {
    @Override
    public void work() {
        System.out.println("👤 Humano trabajando...");
    }
    
    @Override
    public void eat() {
        System.out.println("👤 Humano comiendo...");
    }
    
    @Override
    public void sleep() {
        System.out.println("👤 Humano durmiendo...");
    }
    
    @Override
    public void drive() {
        System.out.println("👤 Humano conduciendo...");
    }
    
    @Override
    public void manageTeam() {
        System.out.println("👤 Humano gestionando equipo...");
    }
}

class ManagerBad implements WorkerBad {
    @Override
    public void work() {
        System.out.println("💼 Gerente trabajando en reuniones...");
    }
    
    @Override
    public void eat() {
        System.out.println("💼 Gerente comiendo...");
    }
    
    @Override
    public void sleep() {
        System.out.println("💼 Gerente durmiendo...");
    }
    
    @Override
    public void drive() {
        // Tal vez el gerente no conduce
        System.out.println("💼 Gerente conduciendo...");
    }
    
    @Override
    public void manageTeam() {
        System.out.println("💼 Gerente gestionando equipo...");
    }
}

// ✅ APLICANDO ISP - Muchas interfaces pequeñas y específicas

// Interface 1: Trabajadores
interface Workable {
    void work();
}

// Interface 2: Seres que necesitan comer
interface Eatable {
    void eat();
}

// Interface 3: Seres que duermen
interface Sleepable {
    void sleep();
}

// Interface 4: Conductores
interface Drivable {
    void drive();
}

// Interface 5: Gerentes
interface Manageable {
    void manageTeam();
}

// ROBOT: Solo lo que un robot REALMENTE hace
class Robot implements Workable {
    @Override
    public void work() {
        System.out.println("🤖 Robot trabajando sin descanso...");
    }
}

// ROBOT AUTÓNOMO: Robot que además puede conducir
class AutonomousRobot implements Workable, Drivable {
    @Override
    public void work() {
        System.out.println("🤖 Robot autónomo trabajando...");
    }
    
    @Override
    public void drive() {
        System.out.println("🤖 Robot autónomo conduciendo...");
    }
}

// HUMANO: Implementa todo lo que un humano realmente hace
class Human implements Workable, Eatable, Sleepable, Drivable {
    @Override
    public void work() {
        System.out.println("👤 Humano trabajando...");
    }
    
    @Override
    public void eat() {
        System.out.println("👤 Humano comiendo...");
    }
    
    @Override
    public void sleep() {
        System.out.println("👤 Humano durmiendo...");
    }
    
    @Override
    public void drive() {
        System.out.println("👤 Humano conduciendo...");
    }
}

// MANAGER: Humano que además gestiona
class Manager implements Workable, Eatable, Sleepable, Manageable {
    @Override
    public void work() {
        System.out.println("💼 Manager trabajando en estrategia...");
    }
    
    @Override
    public void eat() {
        System.out.println("💼 Manager en almuerzo de negocios...");
    }
    
    @Override
    public void sleep() {
        System.out.println("💼 Manager durmiendo (después de mucho trabajo)...");
    }
    
    @Override
    public void manageTeam() {
        System.out.println("💼 Manager gestionando equipo...");
    }
}

// ===============================================
// CÓMO USAR
// ===============================================
class InterfaceSegregationDemo {
    public static void main(String[] args) {
        System.out.println("❌ SIN ISP (mucha duplicación y excepciones):");
        RobotBad robotBad = new RobotBad();
        robotBad.work();
        try {
            robotBad.eat(); // ¡CRASH!
        } catch (UnsupportedOperationException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        
        System.out.println("\n✅ CON ISP (cada clase solo lo que necesita):\n");
        
        // Trabajar con robots
        System.out.println("--- Robots ---");
        Robot robot = new Robot();
        doWork(robot); // Funciona perfecto
        
        AutonomousRobot autoRobot = new AutonomousRobot();
        doWork(autoRobot);
        doDrive(autoRobot); // Solo los que implementan Drivable
        
        // Trabajar con humanos
        System.out.println("\n--- Humanos ---");
        Human human = new Human();
        doWork(human);
        doEat(human);
        doSleep(human);
        doDrive(human);
        
        // Trabajar con managers
        System.out.println("\n--- Managers ---");
        Manager manager = new Manager();
        doWork(manager);
        doEat(manager);
        doManage(manager);
        
        System.out.println("\n✓ Cada clase implementa SOLO lo que necesita");
        System.out.println("✓ Sin excepciones innecesarias");
        System.out.println("✓ Código limpio y específico");
    }
    
    // Métodos que esperan interfaces específicas
    static void doWork(Workable worker) {
        System.out.println("  >> Haciendo trabajar:");
        worker.work();
    }
    
    static void doEat(Eatable eater) {
        System.out.println("  >> Alimentando:");
        eater.eat();
    }
    
    static void doSleep(Sleepable sleeper) {
        System.out.println("  >> Dejando dormir:");
        sleeper.sleep();
    }
    
    static void doDrive(Drivable driver) {
        System.out.println("  >> Haciendo conducir:");
        driver.drive();
    }
    
    static void doManage(Manageable manager) {
        System.out.println("  >> Gestionando:");
        manager.manageTeam();
    }
}

/*
POR QUÉ MEJORA:
✓ CLARIDAD: Robot no implementa métodos que NO puede hacer
✓ FLEXIBILIDAD: Combino solo las interfaces que necesito
✓ SIN DESPERDICIO: No implemento métodos innecesarios
✓ RESPONSABILIDAD CLARA: Cada interfaz tiene UN propósito
✓ FÁCIL DE TESTEAR: Interfaces pequeñas = tests más simples

REGLA: No obliges a implementar métodos que NO vasan a usar

Mejor muchas interfaces pequeñas que una interfaz gorda
*/
