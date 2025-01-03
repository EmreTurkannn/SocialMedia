<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <title>Kişi Kayıt</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .navbar {
            background-color: #343a40; 
        }

        .footer {
            background-color: #6c757d; 
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h2>Kişi Kayıt Formu</h2>
      
        <c:if test="${not empty errorMessage}">
            <div style="color: red;">
                <p>${errorMessage}</p>
            </div>
        </c:if>
        <form action="/register" method="post">
            <div class="mb-3">
                <label for="firstName" class="form-label">Ad</label>
                <input type="text" class="form-control" id="firstName" name="firstName" required>
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Soyad</label>
                <input type="text" class="form-control" id="lastName" name="lastName" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">E-posta</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="gender" class="form-label">Cinsiyet</label><br>
                <input type="radio" id="male" name="gender" value="Erkek" required> Erkek
                <input type="radio" id="female" name="gender" value="Kadın" required> Kadın
            </div>
            <div class="mb-3">
                <label for="hobbies" class="form-label">Hobiler</label><br>
                <!-- Dinamik olarak hobiler burada görünecek -->
                <div id="hobbies-container"></div>
            </div>
            <div class="mb-3">
                <label for="city" class="form-label">Oturduğunuz Şehir</label>
                <select class="form-control" id="city" name="city" required>
                    <option value="İstanbul">İstanbul</option>
                    <option value="Ankara">Ankara</option>
                    <option value="İzmir">İzmir</option>
                    <option value="Bursa">Bursa</option>
                    <option value="Antalya">Antalya</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="bio" class="form-label">Biyografi</label>
                <textarea class="form-control" id="bio" name="bio" rows="3"></textarea>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Şifre</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="mb-3">
                <label for="confirmPassword" class="form-label">Şifre (Tekrar)</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
            </div>
            <button type="submit" class="btn btn-primary">Kaydet</button>
        </form>
        
    </div>

    <script>
        // Backend'den gelen JSON verisini JavaScript'e aktar
        var hobbies = JSON.parse('${hobbiesJson}');
        
        // Veriyi düzgün bir şekilde işlerken, her bir hobiyi eklemek için döngü kullanıyoruz
        if (hobbies && hobbies.length > 0) {
            hobbies.forEach(function(hobby) {
                // Dinamik olarak hobileri checkbox olarak ekleyelim
                var container = document.getElementById("hobbies-container");
                var checkbox = document.createElement("input");
                checkbox.type = "checkbox";
                checkbox.id = "hobby-" + hobby.id;
                checkbox.name = "hobbies";
                checkbox.value = hobby.id;
                var label = document.createElement("label");
                label.htmlFor = checkbox.id;
                label.innerText = hobby.name;
    
                var div = document.createElement("div");
                div.appendChild(checkbox);
                div.appendChild(label);
                container.appendChild(div);
            });
        } else {
            console.log("Hobiler listesi boş.");
        }
    </script>
</body>
</html>
