<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profil</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .profile-img {
            width: 150px;
            height: 150px;
            object-fit: cover;
            border-radius: 50%;
            border: 4px solid #ddd;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: block;
        }

        .profile-section {
            text-align: left;
            margin-top: 20px;
        }

        .comment-card {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 10px;
            border-radius: 8px;
        }

        .commenter-name {
            font-weight: bold;
        }

        .comment-time {
            font-size: 0.9em;
            color: #777;
        }

        .container {
            margin-top: 30px;
        }

        .hobbies-list {
            display: inline; /* Hobiler aynı satırda görünsün */
        }

        .hobbies-list li {
            display: inline;
            margin-right: 10px;
        }

        .lead {
            font-size: 1.25rem; /* Biografi ve hobiler için aynı font büyüklüğü */
            font-weight: 300;
            color: #555;
        }

        .profile-img-container {
            text-align: left; /* Sol tarafa dayalı */
            margin-bottom: 15px;
        }

        .change-photo-btn {
            margin-top: 10px;
            display: inline-block;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <!-- Profil Fotoğrafı ve Fotoğrafı Değiştir Butonu -->
            <div class="col-md-4 profile-section">
                <div class="profile-img-container">
                    <img src="${user.profileImageUrl != null && !user.profileImageUrl.isEmpty() ? user.profileImageUrl : '/images/profilePhotos/user.png'}" class="card-img-top" alt="Profil Fotoğrafı">


                    
                    <!-- Fotoğrafı Değiştir Butonu yalnızca kendi profilinde görünür -->
                    <c:if test="${isOwnProfile}">
                        <button class="btn btn-secondary change-photo-btn" data-bs-toggle="modal" data-bs-target="#changePhotoModal">Fotoğrafı Değiştir</button>
                    </c:if>
                </div>
            </div>

            <!-- Kullanıcı Bilgileri -->
            <div class="col-md-8">
                <h2 class="mb-3">${user.fullName} Profil Sayfası</h1>
                <p class="lead">E-posta: ${user.email}</p>
                <p class="lead">Şehir: ${user.city}</p>
                <p class="lead">Biyografi: ${user.bio}</p>

                <!-- Hobiler -->
                <p class="lead">Hobiler: <span id="hobbies-list"></span></p> <!-- Hobiler burada virgülle listelenecek -->
            </div>
        </div>

        <hr>

        <!-- Yorumlar Bölümü (Tam Genişlikte) -->
        <div class="col-md-12">
            <h3>Yorumlar</h3>
            <div class="list-group">
                <c:forEach var="comment" items="${comments}">
                    <div class="comment-card">
                        <p class="commenter-name">${comment.commenter.fullName}</p>
                        <p class="comment-time">${comment.timestamp}</p>
                        <p>${comment.text}</p>
                    </div>
                </div>
            </div>

            <!-- Yorum Ekleme Bölümü yalnızca başka bir kullanıcının profilinde görünür -->
            <form id="commentForm" action="${pageContext.request.contextPath}/profile/${user.id}/comment" method="post" class="mt-4" style="display: none;">
                <textarea name="commentText" class="form-control" rows="3" placeholder="Yorum yapın..."></textarea>
                <button type="submit" class="btn btn-primary mt-2">Yorum Gönder</button>
            </form>
        </div>
    </div>

    <!-- Fotoğraf Değiştirme Modalı -->
    <div class="modal fade" id="changePhotoModal" tabindex="-1" aria-labelledby="changePhotoModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="changePhotoModalLabel">Profil Fotoğrafını Değiştir</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/profile/change-photo" method="post" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="profilePhoto" class="form-label">Yeni Profil Fotoğrafı Seçin</label>
                            <input type="file" class="form-control" id="profilePhoto" name="profilePhoto" accept="image/*" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Fotoğrafı Yükle</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS ve Modal için gerekli JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // `isOwnProfile`'i JSON formatında geçirelim
        var isOwnProfile = JSON.parse('${isOwnProfile}');
    
        // `hobbiesJson`'u JSON olarak geçirelim
        var hobbies = JSON.parse('${hobbiesJson}');
    
        window.onload = function() {
            const hobbiesList = document.getElementById("hobbies-list");
    
            // Hobileri virgülle ayırarak listeleme
            var hobbiesText = hobbies.map(function(hobby) {
                return hobby.name;
            }).join(', ');

            hobbiesList.textContent = hobbiesText;
    
            // Yorum formunu görünür yapma işlemi
            var commentForm = document.getElementById("commentForm");
    
            // Eğer `isOwnProfile` false ise yorum formunu göster
            if (!isOwnProfile) {
                commentForm.style.display = "block"; // Başka birinin profilindeysen yorum formu görünsün
            } else {
                commentForm.style.display = "none"; // Kendi profilindeyken yorum formu gizlensin
            }
        };
    </script>
</body>
</html>
