// ===============================================
// S - SINGLE RESPONSIBILITY PRINCIPLE
// ===============================================

// ❌ SIN APLICAR - Una clase hace TODO
class UserBad {
    private String name;
    private String email;
    
    // Responsabilidad 1: Datos del usuario
    public String getName() { return name; }
    public String getEmail() { return email; }
    public void setName(String n) { this.name = n; }
    public void setEmail(String e) { this.email = e; }
    
    // Responsabilidad 2: Guardar en BD
    public void saveToDatabase() {
        System.out.println("Guardando " + name + " en BD...");
    }
    
    // Responsabilidad 3: Enviar emails
    public void sendEmail(String message) {
        System.out.println("Enviando email a " + email + ": " + message);
    }
    
    // Responsabilidad 4: Generar reportes
    public void generateReport() {
        System.out.println("Generando reporte de usuario: " + name);
    }
    
    // Responsabilidad 5: Validar datos
    public boolean validateData() {
        return name != null && email != null && email.contains("@");
    }
}

// ✅ APLICANDO SRP - Cada clase UNA responsabilidad

// Clase 1: Solo maneja datos del usuario
class User {
    private String name;
    private String email;
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public String getName() { return name; }
    public String getEmail() { return email; }
    public void setName(String n) { this.name = n; }
    public void setEmail(String e) { this.email = e; }
}

// Clase 2: Responsable de la validación
class UserValidator {
    public boolean validate(User user) {
        String email = user.getEmail();
        return user.getName() != null && email != null && email.contains("@");
    }
}

// Clase 3: Responsable de guardar en BD
class UserRepository {
    public void save(User user) {
        System.out.println("Guardando " + user.getName() + " en BD...");
        // Lógica de BD aquí
    }
    
    public User findByEmail(String email) {
        // Buscar en BD
        return null;
    }
}

// Clase 4: Responsable de enviar emails
class EmailService {
    public void sendWelcomeEmail(User user) {
        System.out.println("Enviando email de bienvenida a " + user.getEmail());
    }
    
    public void sendNotification(User user, String message) {
        System.out.println("Enviando a " + user.getEmail() + ": " + message);
    }
}

// Clase 5: Responsable de reportes
class ReportGenerator {
    public void generateUserReport(User user) {
        System.out.println("=== REPORTE DE USUARIO ===");
        System.out.println("Nombre: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }
}

// ===============================================
// CÓMO USAR
// ===============================================
class SingleResponsibilityDemo {
    public static void main(String[] args) {
        System.out.println("❌ SIN SRP:");
        UserBad userBad = new UserBad();
        userBad.setName("Juan");
        userBad.setEmail("juan@email.com");
        userBad.validateData();
        userBad.saveToDatabase();
        userBad.sendEmail("Bienvenido!");
        userBad.generateReport();
        // PROBLEMA: Si quiero cambiar la lógica de BD, afecta toda la clase
        
        System.out.println("\n✅ CON SRP:");
        // Crear usuario
        User user = new User("Juan", "juan@email.com");
        
        // Validar (responsabilidad separada)
        UserValidator validator = new UserValidator();
        if (validator.validate(user)) {
            // Guardar (responsabilidad separada)
            UserRepository repo = new UserRepository();
            repo.save(user);
            
            // Enviar email (responsabilidad separada)
            EmailService emailService = new EmailService();
            emailService.sendWelcomeEmail(user);
            
            // Generar reporte (responsabilidad separada)
            ReportGenerator report = new ReportGenerator();
            report.generateUserReport(user);
        }
        
        // VENTAJA: Cambiar BD, emails o reportes NO afecta al resto del código
    }
}

/*
POR QUÉ MEJORA:
✓ Código más organizado y legible
✓ Fácil de testear (test UserValidator sin tocar BD)
✓ Si BD falla, no afecta el envío de emails
✓ Reutilizable (EmailService se puede usar en otras clases)
✓ Menos acoplamiento = menos bugs
*/
