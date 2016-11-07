# Java Lessons

This repository contains the __Java Lesson__ sources for the free student courses, held at the TU Dresden.

Exercises to go along with the courses can be found [on GitHub pages](http://fsr.github.io/java-lessons/) or on the `gh-pages` branch of this repository as Markdown sources.


### Teaching

The `tex` files are automatically rendered and can be found at:  
[http://fsr.github.io/java-lessons/materials.html](http://fsr.github.io/java-lessons/materials.html)

Almost every task is linked to a lesson. The corresponding PDF is linked automatically at the beginning of every task.


### Contributing

To add new slides, copy the template to the slides folder:
```
cp latex/templates/new_slide.tex latex/slides/xx_fancy_name.tex
```

After you're done, add the name of the file to the `build_conf.json` to enable automatic building (remember to place a `,` on the line before!).  
Also, add the information to the `_data/materials.yml` residing in the __gh-pages__ branch to link it on the _materials overview_.



These lessons are created and maintained by [@mangerlahn](https://github.com/mangerlahn), [@Pingu501](https://github.com/Pingu501) and [@LeonardFollner](https://github.com/LeonardFollner).
