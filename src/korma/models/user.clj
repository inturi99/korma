(ns korma.models.user
  (:require [korma.db.entities :as db])
  (:use korma.core))

;; get all users
(defn get-all-users []
  (select  db/users))

;; Select User Id
(defn get [id]
  (select  db/users
           (where (= :id id))))

;; insert user
(defn add-user [first last]
  (insert db/users
          (values {:first first :last last})))
;;(insert-user {:first "Krishna" :last "Rao"})

;; delete user
(defn delete-user [id]
  (delete db/users
          (where (= :id id))))

;; update -user
(defn update-user [user id]
  (update  db/users
           (set-fields user)
           (where {:id [= id]})))
;;(update-user {:first "KrishnaRao" :last "Inturi"} 1)

;; you can get a string of the SQL instead of executing
;; by using the sql-only mode

;;add address
(defn add-address [addres]
  (insert db/address
          (values addres)))

;;Delete user Address
(defn delete-address [id]
  (delete db/address
          (where (= :id id))))

(defn update-address [addre users_id]
  (update  db/address
           (set-fields addre)
           (where {:users_id [= users_id]})))
(defn get-sql-only [id]
  (sql-only
   (select  db/address
            (where (= :users_id id)))))

;; get all address
(defn get-all-address []
  (select db/address))

;; select user id
(defn get-addrees [userid]
  (select db/address
          (where (= :users_id userid))))

;;add address
(defn add-email [email]
  (insert db/email
          (values email)))

;;Delete user Address
(defn delete-email [id]
  (delete db/email
          (where (= :id id))))

(defn update-email [email user_id]
  (update  db/email
           (set-fields email)
           (where {:user_id [= user_id]})))
;; get all address
(defn get-all-email []
  (select db/email))

;; select user id
(defn get-email [userid]
  (select db/email
          (where (= :user_id userid))))

;;add posts
(defn add-posts [posts]
  (insert db/posts
          (values posts)))

;;Delete user posts
(defn delete-posts [id]
  (delete db/posts
          (where (= :id id))))

(defn update-posts [posts id]
  (update  db/posts
           (set-fields posts)
           (where {:id [= id]})))
;; get all posts
(defn get-all-posts []
  (select db/posts))

;; select post id
(defn get-posts [id]
  (select db/posts
          (where (= :id id))))

;;add account
(defn add-account [acc]
  (insert db/account
          (values acc)))

;;Delete account
(defn delete-account [user_id]
  (delete db/account
          (where (= :user_id user_id))))
;;update account 
(defn update-account [acc user_id]
  (update  db/account
           (set-fields acc)
           (where {:user_id [= user_id]})))
;; get all account
(defn get-all-account []
  (select db/account))

;; select acount id
(defn get-posts [user_id]
  (select db/account
          (where (= :user_id user_id))))

;;add usersposts
(defn add-users-posts [up]
  (insert db/users_posts
          (values up)))

;;Delete user posts
(defn delet-users-posts [users_id]
  (delete db/users_posts
          (where (= :users_id users_id))))




