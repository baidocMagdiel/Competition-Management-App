# Competition-Management-App

## **Prezentare generală**
Proiectul de față propune implementarea unei aplicații web pentru realizarea managementului competițiilor de karate. Sistemul conține o bază de date în care sunt stocate datele despre competiții, utilizatori și sportivi. Funcționalitățile aplicației vor fi structurate într-o interfata grafică. Pentru implementarea proiectului se va folosi limbajul Java alături de framework-ul Spring Boot.
## **Soluția propusă**
## 1) Spring Boot
Alături de Java se va folosi și framework-ul Spring Boot, deoarece permite autoconfigurarea pe baza dependințelor, nu necesită configurare XML și oferă POM-uri pentru simplificarea configurației Maven. Astfel, în fișierul *pom.xml* se găsesc dependințe pentru teste(JUnit), conectarea cu baza de date(MySQL Connector) și plugin-ul de Maven.
## 2) Baza de date
Pentru crearea, dezvoltrea și administrarea bazei de date se va utiliza MySQL Workbench care este un mediu de dezvoltare pentru bazele de date de implementate în MySQL.
## Cerințe de sistem
### Actori
| Actor         | Tip       | Descriere         |
|:-------------:|:---------:|:-----------------:|
| Administrator         | Uman      | Persoana care se ocupuda de menenanța sistemului și a bazei de date. |
| Manager de competitii     | Uman      |Persoana care crează/editează/șterge competițiile. |
| Antrenorul      | Uman      | Persoana care înscrie/retrage sportivi din/în competiții |
|Vizitatorul   | Uman      | Persoana care nu are cont. |
### Descriere tipuri de actori
#### *Descriere administrator*
Administratorul va fi cel care va realiza întreaga mentenanță a sistemului. El va avea drepturi depline în adăugarea de noi utilizatori sau eliminarea utilizatorilor deja existenți. De asemenea, administratorul va putea trimite mesaje tuturor utilizatorilor, putând chiar să acorde sau retragă privilegiile acestora. El va fi responsabil de reactualizarea paginii web și de administrarea corectă a bazei de date a sistemului. Administratorul se va conecta la sistem printr-un user și o parolă unică. 
#### *Descriere manager de competiții*
Managerul de competiții este un utilizatorul autorizat sa creeze/editeze/șteargă o competiție și să monitorizeze procesul de înscriere și derulare al competițiilor.
#### *Descriere antrenor*
Antrenorul este un utilizatorul autorizat să creeze un club virtual, să adauge/șteargă/editeze sportivi și sa înscrie sportivi în competiții.
#### *Descriere vizitator*
Vizitatorul, adica utilizator temporar,  va avea dreptul de a vizualiza doar rezultatele competițiilor și clasamentele sportivilor. Acesta poate deveni utilizator autorizat prin înregistrarea în contul paginii printr-un cod de acces.
### Listă de funcționalități
-	Login/ Logout utilizatori
-	Creare/ editare/ stergere cont utilizator
-	Căutare competiție
-	Vizualizare informații despre competiție
-	Înscriere sportiv în “club virtual”
-	Creare/ editare/ ștergere competiție
-	Înscriere sportivi în competiție
-	Vizualizare clasamente
-	Actualizare/vizualizare rezulate competiție

## **Descrierea soluției**
### Diagramele sistemului
### Diagrame use case
#### *Use case Administrator*
![diagrama use case admin user](diagram/administrator.png)
#### *Use case Antrenor*
![diagrama use case antrenor](diagram/antrenor.JPG)
#### *Use case Manager competiții*
![diagrama use case manager](diagram/manager.JPG)
#### *Use case Vizitator*
![diagrama use case vizitator](diagram/vizitator.png)
### **Diagrama principală a arhitecturii**
![diagrama arhitectura](diagram/diag_principala.png)
### **Diagrama de deployment**
![diagrama deployment](diagram/deployment.JPG)
### **Diagrama de clase**
(in lucru)
### **Diagrama de pachete**
(in lucru)
## Design-ul interfeței utilizator
(se va dezvolta ulterior)
### Design patterns: 
### 1)Observer
Folosit pentru a anunța antrenorii despre noi competiții adăugate. 
### 2)Facade
Folosit pentru a realiza conexiunea cu baza de date. Oferă o interfață simplificată a operațiilor asupra bazei de date.
### Conexiunea la baza de date
#### *Diagrama relațională a bazei de date*
![diagrama database](diagram/database.JPG)
#### *Descrierea structurii bazei de date*
Baza de date este alcătuită din 6 tabele ce păstrează date despre utilizatori, sportivi, cluburi, competiții și categorii. Mai există și o tabelă de legătură ce 
#### *Modul de conexiune la baza de date*
Conexiunea la baza de date se realizează prin design pattern-ul singleton, folosit pentru clasa ConnectionFactory. Acțiunile asupra bazei de date de inserare/editare/ștergere sunt realizate de clasa AbstractDAO. Această clasă este structurată conform pattern-ului facade pentru a oferi o interfață simplificată a operațiilor asupra bazei de date. În acest mod, fiecare entitate(tabel) va extinde clasa AbstractDAO și va accesa metodete de acces la baza de date într-un mod transparent.
## Testare și depanare
### Bug-uri întâmpinate
