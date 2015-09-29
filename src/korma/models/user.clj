(ns korma.models.user
  (:require [korma.db.entities :as db])
  (:use korma.core))

;; get all users
(defn get-all []
  (select  db/users))

;; Select User Id
(defn get [id]
  (select  db/users
           (where (= :id id))))

;; insert user
(defn insert-user [user]
  (insert db/users
          (values user)))

;; delete user
(defn delete-user [id]
  (delete db/users
          (where (= :id id))))

;; update -user
(defn update-user [user id]
  (update  db/users
           (set-fields user)
           (where {:id [= id]})))
