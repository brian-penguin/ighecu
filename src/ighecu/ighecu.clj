(ns ighecu.ighecu
  "FIXME: my new org.corfield.new/scratch project.")

(defn exec
  "Invoke me with clojure -X ighecu.ighecu/exec"
  [opts]
  (println "exec with" opts))

(defn -main
  "Invoke me with clojure -M -m ighecu.ighecu"
  [& args]
  (println "-main with" args))
