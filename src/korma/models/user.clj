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
