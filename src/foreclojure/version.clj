(ns foreclojure.version
  (:use foreclojure.utils
        compojure.core
        [clojure.java.shell :only [sh]]))

;; fetch this at load time rather than on demand, so that it's accurate even
;; if someone checks out a different revision to poke at without restarting
;; the server (eg to diagnose bugs in a release)
(def sha (not-empty (:out (sh "git" "rev-parse" "--verify" "HEAD"))))

(def-page version []
  (if sha
    [:p "SHA: "
     [:a {:href (str "http://github.com/dbyrne/4clojure/commit/" sha)} sha]]
    [:p "No git repository found"]))

(defroutes version-routes
  (GET ["/about/version"] [] (version)))
