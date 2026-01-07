Multi-Tenant SaaS OPD Workflow

This system is designed as a multi-tenant hospital OPD (Out-Patient Department) management platform, where multiple hospitals/clinics operate independently on the same backend while sharing infrastructure securely.

Each request is scoped by user role + hospital context, ensuring strict tenant isolation.

1️⃣ User Roles in the System

    The platform supports three types of users, all authenticated via OTP:
    
    Admin – Clinic/Hospital owner or manager
    
    Doctor – Medical practitioner associated with one or more hospitals
    
    Staff / Receptionist – Front-desk staff responsible for appointments
    
    A single person can hold multiple roles across different hospitals.

2️⃣ Authentication & Login Flow (OTP + JWT)
    
    Step 1: OTP Login
    
    User logs in using mobile number or email
    
    OTP is generated and validated
    
    System identifies the User identity (global)
    
    Step 2: Initial JWT Issued
    
    After OTP verification:
    
    A JWT token is generated with:
    
    userId
    
    role
    
    hospitalId = null
    
    At this stage, the user is authenticated but not yet scoped to a hospital.

3️⃣ Hospital Selection (Tenant Context Binding)

    If a user is associated with multiple hospitals, the frontend displays a hospital selection screen.
    
    Step 3: Select Hospital
    
    User selects a hospital
    
    Frontend calls /select-hospital
    
    Backend validates:
    
    User is linked to the selected hospital
    
    Role permissions match

Step 4: Regenerated JWT

    A new JWT is issued with:
    
    userId
    
    role
    
    hospitalId (selected hospital)

➡️ From this point onward, every API request is tenant-aware.

4️⃣ Tenant Isolation Strategy

    Every OPD operation is scoped by:
    
    userId + role + hospitalId


Data access is enforced at:

        Controller level
        
        Service layer
        
        Repository queries

Example:

    SELECT * FROM appointments
    WHERE hospital_id = :hospitalId;


This ensures:

> Hospital A cannot access Hospital B’s data
> Same doctor can safely operate across multiple hospitals

5️⃣ OPD Appointment Booking Flow
Step 1: Patient Registration

    Staff registers patient using phone number
    
    Existing patients are auto-detected
    
    Step 2: Appointment Booking
    
    Staff selects:
    
    Doctor
    
    Date & time slot

Backend performs:

    Doctor availability check
    
    Slot conflict prevention
    
    Transaction-safe booking
    
    Step 3: Transaction Safety
    
    Appointment booking runs inside @Transactional
    
    Prevents:
    
    Double booking

Race conditions under concurrent requests

6️⃣ Doctor Dashboard Flow

    When a doctor logs in:
    
    Selects hospital (if multiple)
    
    Dashboard loads:
    
    Today’s appointments
    
    Patient queue
    
    OPD status
    
    All data is fetched using hospital-scoped JWT.

7️⃣ Security Design

    Stateless authentication using JWT
    
    Custom OncePerRequestFilter:
    
    Validates token
    
    Extracts user context
    
    Sets Spring Security Context

Role-based authorization using:

    ADMIN
    
    DOCTOR
    
    STAFF

8️⃣ Scalability & Concurrency

    Stateless APIs → horizontally scalable
    
    Multiple hospitals & staff can book appointments simultaneously
    
    Database consistency ensured using:
    
    Proper indexing
    
    Transaction boundaries

9️⃣ Real-World Design Benefits

✔ Supports real clinic workflows
✔ Allows same person to act as Admin/Doctor across hospitals
✔ Works reliably under concurrent load
✔ Easily extensible for:

WhatsApp notifications

Billing

Analytics

Redis caching

🔧 Tech Stack

Java, Spring Boot

Spring Security (JWT)

Hibernate / JPA

MySQL

REST APIs
