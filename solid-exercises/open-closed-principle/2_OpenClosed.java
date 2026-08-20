// ===============================================
// O - OPEN/CLOSED PRINCIPLE
// ===============================================
// Abierto para EXTENSIÓN, cerrado para MODIFICACIÓN

// ❌ SIN APLICAR - Si agrego un método de pago, debo modificar la clase
class PaymentProcessorBad {
    public void processPayment(String paymentType, double amount) {
        if (paymentType.equals("credit")) {
            processCreditCard(amount);
        } 
        else if (paymentType.equals("paypal")) {
            processPayPal(amount);
        }
        else if (paymentType.equals("bitcoin")) {
            processBitcoin(amount);
        }
        // PROBLEMA: ¿Y si quiero agregar "transferencia"?
        // DEBO MODIFICAR ESTA CLASE = Riesgo de romper el código existente
    }
    
    private void processCreditCard(double amount) {
        System.out.println("Procesando tarjeta de crédito: $" + amount);
    }
    
    private void processPayPal(double amount) {
        System.out.println("Procesando PayPal: $" + amount);
    }
    
    private void processBitcoin(double amount) {
        System.out.println("Procesando Bitcoin: $" + amount);
    }
}

// ✅ APLICANDO OCP - Abierto para extensión sin modificación

// Interfaz: Define contrato para nuevos métodos de pago
interface PaymentMethod {
    void pay(double amount);
    void validate();
}

// Implementación 1: Tarjeta de crédito
class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void validate() {
        System.out.println("Validando tarjeta: " + cardNumber);
    }
    
    @Override
    public void pay(double amount) {
        validate();
        System.out.println("✓ Pago con tarjeta de crédito: $" + amount);
    }
}

// Implementación 2: PayPal
class PayPalPayment implements PaymentMethod {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void validate() {
        System.out.println("Validando cuenta PayPal: " + email);
    }
    
    @Override
    public void pay(double amount) {
        validate();
        System.out.println("✓ Pago con PayPal: $" + amount);
    }
}

// Implementación 3: Bitcoin
class BitcoinPayment implements PaymentMethod {
    private String walletAddress;
    
    public BitcoinPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }
    
    @Override
    public void validate() {
        System.out.println("Validando wallet Bitcoin: " + walletAddress);
    }
    
    @Override
    public void pay(double amount) {
        validate();
        System.out.println("✓ Pago con Bitcoin: $" + amount);
    }
}

// NUEVO: Agregar transferencia SIN MODIFICAR NADA
class BankTransferPayment implements PaymentMethod {
    private String accountNumber;
    
    public BankTransferPayment(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    @Override
    public void validate() {
        System.out.println("Validando cuenta bancaria: " + accountNumber);
    }
    
    @Override
    public void pay(double amount) {
        validate();
        System.out.println("✓ Pago por transferencia bancaria: $" + amount);
    }
}

// Procesador: NUNCA SE MODIFICA, solo recibe PaymentMethod
class PaymentProcessor {
    public void processPayment(PaymentMethod method, double amount) {
        System.out.println("--- Procesando pago ---");
        method.pay(amount);
        System.out.println("--- Pago completado ---\n");
    }
}

// ===============================================
// CÓMO USAR
// ===============================================
class OpenClosedDemo {
    public static void main(String[] args) {
        System.out.println("❌ SIN OCP:");
        PaymentProcessorBad processorBad = new PaymentProcessorBad();
        processorBad.processPayment("credit", 100.0);
        processorBad.processPayment("paypal", 50.0);
        // ¿Quiero agregar Bitcoin? DEBO MODIFICAR LA CLASE
        
        System.out.println("✅ CON OCP:");
        PaymentProcessor processor = new PaymentProcessor();
        
        // Pago con tarjeta
        PaymentMethod creditCard = new CreditCardPayment("1234-5678-9012");
        processor.processPayment(creditCard, 100.0);
        
        // Pago con PayPal
        PaymentMethod paypal = new PayPalPayment("usuario@email.com");
        processor.processPayment(paypal, 50.0);
        
        // Pago con Bitcoin
        PaymentMethod bitcoin = new BitcoinPayment("1A1z7agoat");
        processor.processPayment(bitcoin, 0.005);
        
        // NUEVO: Agregar transferencia bancaria SIN MODIFICAR PaymentProcessor
        PaymentMethod bankTransfer = new BankTransferPayment("123-456-789");
        processor.processPayment(bankTransfer, 200.0);
        
        System.out.println("¡Agregué transferencia sin tocar PaymentProcessor!");
    }
}

/*
POR QUÉ MEJORA:
✓ EXTENSIÓN fácil: agregar nuevos métodos de pago es solo crear una clase nueva
✓ MODIFICACIÓN no necesaria: PaymentProcessor nunca cambia
✓ MENOS RIESGO: cambios en un PaymentMethod no afectan otros
✓ CÓDIGO ESCALABLE: cada nuevo método de pago es independiente
✓ TESTING: puedo testear cada método de pago por separado
*/
