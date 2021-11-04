Denna uppgift består av två delar. Den ena delen handlar om att bygga ett web-API. Den andra delen handlar om att bygga en frontend som använder APIet.

 

Uppgiften täcker punkterna 7, 8 och 9 i kursplanen. Bedömning är icke godkänt, godkänt och väl godkänt. Det är tillåtet att hjälpa klasskamrater och samarbeta, samt att söka information på internet, men man skall lämna in ett eget projekt. Uppgiften skall vara inlämnad senast Söndag den 7:e (2021-11-7). Lämna in i form av filer eller lägg upp på en git provider (github, gitlab) och skicka länk.

 

Du skall i denna uppgift bygga en klon av reddit (Reddit - Dive into anything), men en enklare version.

 

Reddit är en platform där man kan ladda upp ”posts” med en titel plus en text som beskriver något som man vill säga. Det kan handla om vad som helst. Sedan kan andra personer gå in och gilla, eller ogilla posts. I varje post kan man också skriva kommentarer och hålla diskussioner mellan andra personer som också kollar på samma post.

 

Du skall bygga en web server och en frontend där man skall kunna:

Registrera användare
Logga in på användare
Logga ut från användare
Se posts (alla)
Skapa posts (men bara om man har en användare och är inloggad)
Ta bort posts (bara sina egna posts på sin användare, och man måste vara inloggad)
 

En post skall bestå av minst en titel, en text och vem som gjorde den. Man behöver inte kunna lägga upp bilder eller videor, utan text är bra nog.

 

Bygg webbservern med Java och Spring Boot. I frontend delen väljer du valfritt vilken teknologi du använder, till exempel React.

 

För G:

Följ kraven ovanför och försök att följa REST principerna så gott det går.

 

För VG:

Följ kraven för G. Du skall även bygga på platformen så att den kan hantera ”upvotes” och ”downvotes”. På varje post skall man kunna göra en ”upvote” för att säga att man gillar den, eller ”downvote” för att säga att man ogillade den. Antalet upvotes och downvotes skall visas upp för en post (på valfritt ställe). Man måste vara inloggad för att kunna använda denna funktion och man kan bara ”upvote” eller ”downvote” en gång. Du kan samtidigt bara göra ena eller den andra. Man skall inte kunna både ”upvote” och ”downvote”.

Du skall även för VG inkorporera en databas. Använd valfri databas (ex: MySQL) och spara all data där (användare, posts o.s.v).

 

 

Lycka till!
