(ns korma.views)

(ns korma.views
  (:require [korma.models.user :as db]
            [clojure.string :as str]
            [hiccup.page :as hic-p]))

(defn gen-page-head
  [title]
  [:head
   [:title (str "Users: " title)]
   (hic-p/include-css "/css/styles.css")])

(def header-links
  [:div#header-links
   "[ "
   [:a {:href "/"} "Home"]
   " | "
   [:a {:href "/add-user"} "Add a Users"]
   " | "
   [:a {:href "/get-all-users"} "View All Users"]
   " ]"])

(defn home-page
  []
  (hic-p/html5
   (gen-page-head "Home")
   header-links
   [:h1 "Home"]
   [:p "Webapp to store and display some 2D (first,last) users."]))

(defn add-user-page
  []
  (hic-p/html5
   (gen-page-head "Add a User")
   header-links
   [:h1 "Add a User"]
   [:form {:action "/add-user" :method "POST"}
    [:p "First: " [:input {:type "text" :name "first"}]]
    [:p "last: " [:input {:type "text" :name "last"}]]
    [:p [:input {:type "submit" :value "submit user"}]]]))

(defn add-user-results-page
  [{:keys [first last]}]
  (let [id (db/add-user first last)]
    (hic-p/html5
     (gen-page-head "Added a User")
     header-links
     [:h1 "Added a user"]
     [:p "Added [" first ", " last "] (id: " id ") to the db. "
      [:a {:href (str "/users/" id)} "See for yourself"]
      "."])))

;; (defn location-page
;;   [loc-id]
;;   (let [{x :x y :y} (db/get-xy loc-id)]
;;     (hic-p/html5
;;      (gen-page-head (str "Location " loc-id))
;;      header-links
;;      [:h1 "Update Location"]
;;      [:form {:action "/update-location" :method "POST"}
;;      [:p "Location Id : " loc-id ]
;;      [:p "x value : " [:input {:type "text" :name "x" :value x}]]
;;      [:p "y value : " [:input {:type "text" :name "y" :value y}]]
;;      [:p [:input {:type "submit" :value "update location"}]]
;;       ])))

(defn all-users-page
  []
  (let [allusers (db/get-all-users)]
    (hic-p/html5
     (gen-page-head "All Users in the db")
     header-links
     [:h1 "All users"]
     [:table
      [:tr [:th "id"] [:th "first"] [:th "last"] [:th " "] [:th " "]]
      (for [usr allusers]
        [:tr [:td (:id usr)] [:td (:first usr)] [:td (:last usr)] [:td [:a {:href "/add-user"} "Update"]]  [:td [:a {:href "/delete-user"  :id usr} "Delete"]]])])))


(defn delete-user-results-page
  [{:keys [id]}]
  (let [id (db/delete-user id)]
    (hic-p/html5
     (gen-page-head "All Users in db"))))
