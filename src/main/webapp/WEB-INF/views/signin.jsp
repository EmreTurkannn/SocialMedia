<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <title>Giriş</title>
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
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Social Sharing</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/register">Kişi Kayıt</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/signin">Oturum Açma</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/forum">Forum</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users">Kişiler</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

<div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="card" style="width: 400px;">
        <div class="card-body">
            <h3 class="card-title text-center mb-4">Giriş Yap</h3>
            <form action="/signin" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">E-posta</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Şifre</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Giriş Yap</button>
                <div class="mt-3 text-center">
                    <p class="text-danger" th:if="${errorMessage}" th:text="${errorMessage}"></p>
                </div>
                <div class="mt-2 text-center">
                    <p>Hesabınız yok mu? <a href="${pageContext.request.contextPath}/register">Kayıt olun</a></p>
                </div>
            </form>
        </div>
    </div>
</div>

<footer class="footer text-white text-center py-3">
    <p>&copy; 2024 Social Sharing. Tüm hakları saklıdır.</p>
</footer>
</body>
</html>
