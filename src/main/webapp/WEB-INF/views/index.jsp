<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <title>Anasayfa</title>
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

    <div class="container my-5">
        <div class="p-5 mb-4 bg-light rounded-3">
            <div class="container-fluid py-5">
                <h1 class="display-5 fw-bold">Social Sharing Platformuna Hoş Geldiniz!</h1>
                <p class="col-md-8 fs-4">Platformumuzda yeni kişilerle tanışabilir, forumlarda tartışabilir ve profil oluşturabilirsiniz.</p>
                <a class="btn btn-primary btn-lg" href="/register" role="button">Hemen Kayıt Ol</a>
            </div>
        </div>
    </div>

    <footer class="footer text-white text-center py-3">
        <p>&copy; 2024 Social Sharing. Tüm hakları saklıdır.</p>
    </footer>
</body>
</html>
