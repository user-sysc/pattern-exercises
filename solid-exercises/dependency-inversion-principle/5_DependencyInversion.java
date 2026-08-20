// ===============================================
// D - DEPENDENCY INVERSION PRINCIPLE
// ===============================================
// Depende de ABSTRACCIONES, NO de implementaciones concretas
// Las dependencias van de arriba hacia abajo (inyección)

// ❌ SIN APLICAR - Acoplado a implementaciones concretas
class EmailServiceBad {
    public void send(String to, String message) {
        System.out.println("📧 Enviando email a " + to + ": " + message);
    }
}

class SMSServiceBad {
    public void send(String phone, String message) {
        System.out.println("📱 Enviando SMS a " + phone + ": " + message);
    }
}

// PROBLEMA: UserService está acoplada a EmailService
// Si quiero cambiar a SMS, ¡DEBO MODIFICAR UserService!
class UserServiceBad {
    private EmailServiceBad emailService = new EmailServiceBad();
    
    public void registerUser(String email, String name) {
        System.out.println("Registrando usuario: " + name);
        emailService.send(email, "Bienvenido " + name);
    }
    
    public void resetPassword(String email) {
        System.out.println("Reseteando contraseña...");
        emailService.send(email, "Link para resetear: https://...");
    }
}

// ✅ APLICANDO DIP - Depende de ABSTRACCIONES

// 1. INTERFAZ (Abstracción): Define el contrato
interface NotificationService {
    void send(String destination, String message);
}

// 2. IMPLEMENTACIÓN 1: Email
class EmailService implements NotificationService {
    @Override
    public void send(String email, String message) {
        System.out.println("📧 Enviando email a " + email + ": " + message);
    }
}

// 2. IMPLEMENTACIÓN 2: SMS
class SMSService implements NotificationService {
    @Override
    public void send(String phone, String message) {
        System.out.println("📱 Enviando SMS a " + phone + ": " + message);
    }
}

// 2. IMPLEMENTACIÓN 3: WhatsApp (agregar sin modificar UserService)
class WhatsAppService implements NotificationService {
    @Override
    public void send(String phone, String message) {
        System.out.println("💬 Enviando WhatsApp a " + phone + ": " + message);
    }
}

// 2. IMPLEMENTACIÓN 4: Slack
class SlackService implements NotificationService {
    @Override
    public void send(String username, String message) {
        System.out.println("⚙️  Enviando a Slack (@" + username + "): " + message);
    }
}

// 3. SERVICIO PRINCIPAL: Depende de la ABSTRACCIÓN, no de implementaciones
class UserService {
    // INYECCIÓN DE DEPENDENCIA: el servicio se proporciona desde afuera
    private NotificationService notificationService;
    
    // Constructor: recibe la dependencia inyectada
    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    // Setter: alternativa para cambiar el servicio en tiempo de ejecución
    public void setNotificationService(NotificationService service) {
        this.notificationService = service;
    }
    
    public void registerUser(String destination, String name) {
        System.out.println("Registrando usuario: " + name);
        notificationService.send(destination, "Bienvenido " + name + "!");
    }
    
    public void resetPassword(String destination) {
        System.out.println("Reseteando contraseña...");
        notificationService.send(destination, "Link para resetear: https://...");
    }
    
    public void notifyPromotion(String destination) {
        System.out.println("Notificando promoción...");
        notificationService.send(destination, "¡Tenemos una oferta especial para ti!");
    }
}

// ===============================================
// INYECCIÓN DE DEPENDENCIAS EN ACCIÓN
// ===============================================
class DependencyContainer {
    // Factory para crear servicios
    public static NotificationService createEmailService() {
        return new EmailService();
    }
    
    public static NotificationService createSMSService() {
        return new SMSService();
    }
    
    public static NotificationService createWhatsAppService() {
        return new WhatsAppService();
    }
    
    public static NotificationService createSlackService() {
        return new SlackService();
    }
}

// ===============================================
// CÓMO USAR
// ===============================================
class DependencyInversionDemo {
    public static void main(String[] args) {
        System.out.println("❌ SIN DIP (Acoplado):");
        UserServiceBad userBad = new UserServiceBad();
        userBad.registerUser("juan@email.com", "Juan");
        userBad.resetPassword("juan@email.com");
        // PROBLEMA: ¿Y si quiero SMS? DEBO CAMBIAR UserServiceBad
        
        System.out.println("\n✅ CON DIP (Flexible con Inyección):\n");
        
        // OPCIÓN 1: Usar Email
        System.out.println("--- Usando Email ---");
        UserService userWithEmail = new UserService(
            DependencyContainer.createEmailService()
        );
        userWithEmail.registerUser("juan@email.com", "Juan");
        userWithEmail.resetPassword("juan@email.com");
        
        // OPCIÓN 2: Cambiar a SMS SIN MODIFICAR UserService
        System.out.println("\n--- Cambiar a SMS (sin tocar UserService) ---");
        UserService userWithSMS = new UserService(
            DependencyContainer.createSMSService()
        );
        userWithSMS.registerUser("+573001234567", "María");
        userWithSMS.resetPassword("+573001234567");
        
        // OPCIÓN 3: Usar WhatsApp
        System.out.println("\n--- Usando WhatsApp ---");
        UserService userWithWhatsApp = new UserService(
            DependencyContainer.createWhatsAppService()
        );
        userWithWhatsApp.registerUser("+573009876543", "Pedro");
        userWithWhatsApp.notifyPromotion("+573009876543");
        
        // OPCIÓN 4: Usar Slack
        System.out.println("\n--- Usando Slack ---");
        UserService userWithSlack = new UserService(
            DependencyContainer.createSlackService()
        );
        userWithSlack.registerUser("carlos", "Carlos");
        userWithSlack.notifyPromotion("carlos");
        
        // OPCIÓN 5: Cambiar en tiempo de ejecución
        System.out.println("\n--- Cambiar servicio en tiempo de ejecución ---");
        UserService flexibleUser = new UserService(
            DependencyContainer.createEmailService()
        );
        flexibleUser.registerUser("ana@email.com", "Ana");
        
        // Cambiar a SMS sin crear un nuevo UserService
        flexibleUser.setNotificationService(
            DependencyContainer.createSMSService()
        );
        flexibleUser.resetPassword("+573115556666");
        
        System.out.println("\n✓ UserService NUNCA se modificó");
        System.out.println("✓ Puedo agregar nuevos servicios fácilmente");
        System.out.println("✓ Testing: puedo inyectar un MockNotificationService");
    }
}

// ===============================================
// BONUS: Mock para Testing
// ===============================================
class MockNotificationService implements NotificationService {
    @Override
    public void send(String destination, String message) {
        System.out.println("[MOCK] Notificación enviada a " + destination);
    }
}

class TestUserService {
    public static void testRegisterUser() {
        System.out.println("\n🧪 TESTING CON MOCK:");
        NotificationService mockService = new MockNotificationService();
        UserService userService = new UserService(mockService);
        
        // En testing, uso Mock en lugar de Email/SMS real
        userService.registerUser("test@test.com", "Test User");
        System.out.println("✓ Test completado sin enviar emails reales");
    }
    
    public static void main(String[] args) {
        testRegisterUser();
    }
}

/*
POR QUÉ MEJORA:
✓ FLEXIBILIDAD: Cambio de servicio sin modificar UserService
✓ TESTING: Inyecto MockNotificationService para tests
✓ ESCALABILIDAD: Agregar Email2, SMS2, PushNotification sin cambiar nada
✓ DESACOPLAMIENTO: UserService no conoce detalles de Email/SMS
✓ MANTENIMIENTO: Si Email cambia, solo EmailService se modifica

TRES FORMAS DE INYECTAR:
1. Constructor (recomendado)
2. Setter
3. Interface (inyección en método)

REGLA: Depende de lo que HACE (NotificationService),
       NO de lo que ES (EmailService)
*/
