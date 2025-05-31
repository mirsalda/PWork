# PWork - Platforma per Menaxhimin e Punesimeve

## Përmbledhje

PWork eshte nje platforme per lidhjen mes punedhenesve (Recruiters) dhe kerkesave per pune (Job Seekers). Projekti eshte zhvilluar me Java Spring Boot dhe perdor JPA per menaxhimin e databazes. Ai perfshin menaxhimin e perdoruesve, ofertave te punes, aplikimeve, dhe funksionalitete si ruajtja e puneve te preferuara.

## Teknologjite e Përdorura

- Java 17+
- Spring Boot
- JPA / Hibernate
- Lombok
- Jakarta Validation (Bean Validation)
- PostgreSQL 
- Maven (menaxhimi i dependencies)

## Entitetet Kryesore

- **User**: Entiteti bazik i perdoruesve me role RECRUITER ose JOB_SEEKER.
- **Recruiter**: Informacione per punedhenesin.
- **JobSeeker**: Informacione per kerkesat per pune.
- **JobPost**: Oferta pune e krijuar nga nje Recruiter.
- **Application**: Aplikim per nje pune nga nje JobSeeker, me status dhe informacione per CV.
- **SavedJob**: Ruajtja e punove te preferuara nga JobSeeker.

## Funksionalitetet Kryesore

- Regjistrim dhe menaxhim i perdoruesve me role te ndryshem.
- Krijim dhe menaxhim i ofertave te punes.
- Aplikime per ofertat e punes .
- Ruajtja e ofertave te punes ne liste preferencash.
- Validim i te dhenave me Jakarta Bean Validation.
- Modelim i marredhenieve ne databaze me JPA.


