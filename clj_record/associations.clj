(in-ns 'clj_record.core)

(defn has-many [model-name association-name]
  (let [associated-model-name (singularize (name association-name))
        foreign-key-attribute (keyword (str model-name "_id"))
        find-fn-name (symbol (str "find-" association-name))
        clear-fn-name (symbol (str "clear-" association-name))]
    `(do
      (defn ~find-fn-name [record#]
        (find-records ~associated-model-name {~foreign-key-attribute (record# :id)}))
      (defn ~clear-fn-name [record#]
        (println "Can't delete things yet!")))))

(defn belongs-to [model-name association-name]
  (let [associated-model-name (str association-name)
        find-fn-name (symbol (str "find-" associated-model-name))
        foreign-key-attribute (keyword (str associated-model-name "_id"))]
    `(defn ~find-fn-name [record#]
      (find-record ~associated-model-name (~foreign-key-attribute record#)))))
