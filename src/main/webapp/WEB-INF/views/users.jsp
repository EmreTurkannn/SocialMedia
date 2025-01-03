
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kullanıcılar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Tüm Kullanıcılar</h1>
        <div class="row">
            <c:forEach var="user" items="${users}">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <!-- Profil resmi kontrolü -->
                        <c:if test="${not empty user.profileImageUrl}">
                            <img src="${user.profileImageUrl}" class="card-img-top" alt="Profil Fotoğrafı">
                        </c:if>
                        <c:if test="${empty user.profileImageUrl}">
                            <img src="/images/profilPhotos/user.png" class="card-img-top" alt="Profil Fotoğrafı">
                        </c:if>
                        <div class="card-body">
                            <!-- Kullanıcı adı ve soyadı -->
                            <h5 class="card-title">${user.firstName} ${user.lastName}</h5>
                            <a href="/profile/${user.id}" class="btn btn-primary">Profiline Git</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
