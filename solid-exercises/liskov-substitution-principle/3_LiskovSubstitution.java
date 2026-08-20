// ===============================================
// L - LISKOV SUBSTITUTION PRINCIPLE
// ===============================================
// Una subclase DEBE poder reemplazar a su padre sin romper nada

// ❌ SIN APLICAR - Penguin no puede hacer lo que Bird promete
abstract class BirdBad {
    abstract void fly();
    abstract void eat();
}

class SparrowBad extends BirdBad {
    @Override
    public void fly() {
        System.out.println("🐦 Gorrión volando...");
    }
    
    @Override
    public void eat() {
        System.out.println("🐦 Gorrión comiendo semillas...");
    }
}

class PenguinBad extends BirdBad {
    @Override
    public void fly() {
        // PROBLEMA: ¡Los pingüinos no vuelan!
        throw new UnsupportedOperationException("Los pingüinos no pueden volar");
    }
    
    @Override
    public void eat() {
        System.out.println("🐧 Pingüino comiendo pescado...");
    }
}

// PROBLEMA: Esto NO funciona
// void makeAFly(BirdBad bird) { 
//     bird.fly(); // ¿Qué pasa si es un Penguin? ¡CRASH!
// }

// ✅ APLICANDO LSP - Cada clase hace SOLO lo que puede

// Clase base: Solo lo que TODOS los pájaros hacen
abstract class Bird {
    abstract void eat();
    
    public void sleep() {
        System.out.println("😴 Pájaro durmiendo...");
    }
}

// Interfaz: Solo los pájaros que vuelan
interface Flyable {
    void fly();
}

// Pájaros que vuelan
class Sparrow extends Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("🐦 Gorrión volando rápido...");
    }
    
    @Override
    public void eat() {
        System.out.println("🐦 Gorrión comiendo semillas...");
    }
}

class Eagle extends Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("🦅 Águila volando alto...");
    }
    
    @Override
    public void eat() {
        System.out.println("🦅 Águila cazando...");
    }
}

// Pájaros que NO vuelan
class Penguin extends Bird {
    // NO implementa Flyable
    
    public void swim() {
        System.out.println("🐧 Pingüino nadando en el océano...");
    }
    
    @Override
    public void eat() {
        System.out.println("🐧 Pingüino comiendo pescado...");
    }
}

class Ostrich extends Bird {
    // NO implementa Flyable
    
    public void run() {
        System.out.println("🤫 Avestruz corriendo...");
    }
    
    @Override
    public void eat() {
        System.out.println("🤫 Avestruz comiendo plantas...");
    }
}

// ===============================================
// FUNCIONES QUE CONFÍAN EN EL CONTRATO
// ===============================================

// Esta función SOLO espera un Bird (comportamiento base)
void feedBird(Bird bird) {
    System.out.println("Alimentando al pájaro...");
    bird.eat();
    bird.sleep();
    // Seguro porque TODO Bird come y duerme
}

// Esta función SOLO espera un Flyable
void makeFly(Flyable bird) {
    System.out.println("Haciendo volar...");
    bird.fly();
    // Seguro porque SOLO implementamos Flyable en pájaros que vuelan
}

// ===============================================
// CÓMO USAR
// ===============================================
class LiskovSubstitutionDemo {
    public static void main(String[] args) {
        System.out.println("❌ SIN LSP:");
        System.out.println("(Si lo ejecuto con Penguin, ¡CRASH!)");
        try {
            BirdBad penguin = new PenguinBad();
            penguin.fly(); // ¡UnsupportedOperationException!
        } catch (UnsupportedOperationException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        
        System.out.println("\n✅ CON LSP:");
        
        // PÁJAROS QUE VUELAN
        System.out.println("--- Pájaros que vuelan ---");
        Bird sparrow = new Sparrow();
        feedBird(sparrow); // Funciona perfecto
        makeFly((Flyable) sparrow); // Funciona perfecto
        
        Bird eagle = new Eagle();
        feedBird(eagle);
        makeFly((Flyable) eagle);
        
        // PÁJAROS QUE NO VUELAN
        System.out.println("\n--- Pájaros que no vuelan ---");
        Bird penguin = new Penguin();
        feedBird(penguin); // ¡Funciona! No llamamos fly()
        // makeFly(penguin); // ¡Error en compilación! Bien, así lo detectamos
        ((Penguin) penguin).swim(); // Hacemos lo que el Pingüino SÍ puede hacer
        
        Bird ostrich = new Ostrich();
        feedBird(ostrich);
        ((Ostrich) ostrich).run();
        
        System.out.println("\n✓ Sin sorpresas: cada pájaro hace lo que sabe hacer");
    }
    
    // Sobrecargar para demostración
    static void feedBird(Bird bird) {
        System.out.println("  Alimentando al pájaro...");
        bird.eat();
    }
    
    static void makeFly(Flyable bird) {
        System.out.println("  Haciendo volar...");
        bird.fly();
    }
}

/*
POR QUÉ MEJORA:
✓ SEGURIDAD: Si recibo un Bird, SÉ que puedo alimentarlo sin sorpresas
✓ EXPECTATIVAS CLARAS: Flyable significa que el objeto PUEDE volar
✓ SIN EXCEPCIONES: No hay "UnsupportedOperationException" en código real
✓ POLYMORFISMO CORRECTO: Las subclases respetan el contrato del padre
✓ FÁCIL DE TESTEAR: Cada tipo de pájaro se comporta como se espera

REGLA: Si una subclase NO puede hacer lo que su padre promete,
       NO debe heredar de ese padre
*/
