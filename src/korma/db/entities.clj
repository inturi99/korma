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

(declare users address)

(defentity users
  (pk :id)
  (table :users)
  (database spec)

  (entity-fields :first :last)

  (prepare (fn [{last :last :as v}]
             (if last
               (assoc v :last (str/upper-case last)) v)))
  ;; captilizes first field on select
  (transform (fn [{first :first :as v}]
               (if first
                 (assoc v :first (str/capitalize first)) v)))


  ;; Relationships
  (has-one address))

(defentity address
  (pk :id)
  (table :address)
  (database spec)
  (belongs-to users)
  (entity-fields :country :users_id))


