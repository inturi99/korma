(ns korma.db.entities
  (:use korma.db)
  (:use korma.core)
  (:require [clojure.string :as str]))

(let [db-host "localhost"
      db-port 5432
      db-name "korma"]
  (def spec {:classname "org.postgresql.Driver" ; must be in classpath
             :subprotocol "postgresql"
             :subname (str "//" db-host ":" db-port "/" db-name)
                                        ; Any additional keys are passed to the driver
                                        ; as driver-specific properties.
             :user "postgres"
             :password "Design_20"}))

(declare users address email account  posts users_posts)

(defentity users
  (pk :id)
  (table :users)
  (database spec)

  (entity-fields :id :first :last)

  (prepare (fn [{last :last :as v}]
             (if last
               (assoc v :last (str/upper-case last)) v)))
  ;; captilizes first field on select
  (transform (fn [{first :first :as v}]
               (if first
                 (assoc v :first (str/capitalize first)) v)))


  ;; Relationships
  (has-one address)
  (has-many email)
  (belongs-to account)
  (many-to-many posts :users_posts))

(defentity address
  (pk :id)
  (table :address)
  (database spec)
  (belongs-to users)
  (entity-fields :id  :country :users_id))

(defentity email
  (pk :id)
  (table :email)
  (database spec)
  (belongs-to users)
  (entity-fields :id  :email :user_id))

(defentity posts
  (database spec)
  (many-to-many users :users_posts))

(defentity account
  (database spec)
  (has-one users))

(defentity users_posts
  (database spec))

 

