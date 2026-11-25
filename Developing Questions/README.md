Searched "BNQF" OBE Bloom taxonomy question paper CO mapping
Searched "BNQF" "Cognitive" "Understanding" "Knowledge" OBE assessment marks
Searched BNQF Bangladesh National Qualifications Framework OBE course outcomes Bloom taxonomy
Searched "BNQF" "Level 7" "Knowledge" "Skills" "Bangladesh Accreditation Council"
Searched "Cognitive" "Knowledge" "Understanding" Bloom taxonomy question paper OBE Bangladesh university
Searched "Knowledge" "Understanding" "Cognitive" "40" marks question paper OBE Bangladesh
Searched "Knowledge" "Understanding" "BNQF" "Bloom" domain

Based on the attached **Software Design** lecture slides (Pages 1–155), below is a complete **OBE-based Assessment Package** aligned with **Revised Bloom's Taxonomy** and the **BNQF Level 7 (Bachelor's Degree) Standard** for a **Software Engineering (SE)** course.

---

## 1. Software Engineering (SE) Course Outcomes (`CO1`–`CO5`)

Designed in accordance with **BNQF Level 7** (Higher Education) descriptors—spanning the **Fundamental Domain** (*Knowledge & Understanding*) and **Thinking Domain** (*Cognitive Application, Analysis & Evaluation*)—across the core topics of the provided slides:

| CO | Course Outcome Statement | Target Bloom's Levels | BNQF Level 7 Domain(s) | Slide Coverage |
| :--- | :--- | :--- | :--- | :--- |
| **CO1** | **Explain** software design fundamentals, design phases, and modular decomposition principles (coupling, cohesion, abstraction, encapsulation, and information hiding) to organize maintainable software components. | `C1`–`C4` | Fundamental (*Knowledge & Understanding*), Thinking (*Cognitive*) | Slides 2–14 |
| **CO2** | **Apply** object-oriented design quality metrics (Chidamber & Kemerer suite: `CBO`, `COF`, `LCOM`, `LCOM2`, `LCOM3`, `WMC`, `RFC`, `DIT`, `NOC`) to quantify and interpret class complexity and cohesion. | `C1`–`C5` | Fundamental (*Knowledge & Understanding*), Thinking (*Cognitive*) | Slides 15–29, 109–118 |
| **CO3** | **Evaluate** software architecture structures and architectural styles (Layered, Event-Based, MVC, Blackboard/Repository, Pipes and Filters) to select suitable high-level architectures for given system requirements. | `C1`–`C5` | Fundamental (*Knowledge & Understanding*), Thinking (*Cognitive*) | Slides 30–39 |
| **CO4** | **Apply** Gang of Four (GoF) design patterns (Creational, Structural, Behavioral) and Fowler's Enterprise Application Architecture (EAA) patterns (Domain/Service Layer, ORM/Inheritance Mapping, Presentation Logic) to solve recurring design problems. | `C1`–`C4` | Fundamental (*Knowledge & Understanding*), Thinking (*Cognitive*) | Slides 40–108, 119–146 |
| **CO5** | **Analyze** user interface designs against core UI principles, interaction modalities, and inclusive information presentation guidelines to improve usability and accessibility for diverse users. | `C1`–`C5` | Fundamental (*Knowledge & Understanding*), Thinking (*Cognitive*), Social (*User Diversity*) | Slides 147–155 |

---

## 2. OBE Examination Question Paper

**Course:** Software Engineering (Module: Software Design)  
**Assessment Standard:** OBE / BNQF Level 7 (Cognitive Domain: Knowledge, Understanding & Higher Cognitive Skills)  
**Total Marks:** 40 &nbsp;|&nbsp; **Time Allowed:** 1 Hour (60 Minutes)  

> **Instructions:**
> - **Answer ALL questions** (~12 minutes per 8-mark question). Under OBE/BNQF assessment guidelines, all questions are compulsory to measure attainment across all five Course Outcomes (`CO1`–`CO5`).
> - Marks, target **CO**, **Bloom's Taxonomy Cognitive Level (`C1`–`C5`)**, and **BNQF Category** are indicated in brackets beside each sub-question.

---

### **Question 1** *(8 Marks — Design Fundamentals & Modularity)*

**(a)** Define *abstraction* and *encapsulation / information hiding* (Parnas, 1972) in the context of modular software design.  
**[2 Marks | CO1 | C1: Knowledge | BNQF: Fundamental – Knowledge]**

**(b)** Distinguish between *architectural design* and *detailed design*, and explain why recording the *rationale* for nontrivial design decisions enhances long-term software maintainability.  
**[3 Marks | CO1 | C2: Understanding | BNQF: Fundamental – Understanding]**

**(c)** Classify the **cohesion** type (*Coincidental, Logical, Temporal, Procedural, Communicational, Sequential,* or *Functional*) exhibited by each module below and justify why one produces a stronger modular split than the other:
- **Module X:** Executes `readSensor()` and passes its output directly to `calibrateData()`, which passes its output to `formatReport()`.
- **Module Y:** Groups unrelated utility methods (`printBanner()`, `connectDB()`, `sortList()`) inside a single `SystemUtils` class.  
**[3 Marks | CO1 | C4: Analyze | BNQF: Thinking – Cognitive]**

---

### **Question 2** *(8 Marks — Software Design Quality & OO Metrics)*

**(a)** State the definitions of **Coupling Between Object classes (`CBO`)** and **Request For a Class (`RFC`)** from the Chidamber & Kemerer (CK) metrics suite, specifying which classes or calls are excluded when counting them.  
**[2 Marks | CO2 | C1: Knowledge | BNQF: Fundamental – Knowledge]**

**(b)** Consider the following Java class in a smart-home project (assume `Heater` and `CoolingFan` are classes within the same project):

```java
class SmartThermostat {
    private double currentTemp;
    private double targetTemp;
    private Heater heater;
    private CoolingFan fan;

    public SmartThermostat(Heater h, CoolingFan f) {
        currentTemp = 22.0;
        targetTemp = 22.0;
        heater = h;
        fan = f;
    }

    public void setTargetTemp(double t) {
        targetTemp = t;
    }

    public void regulate() {
        if (currentTemp < targetTemp) {
            if (heater != null) heater.turnOn();
            if (fan != null) fan.turnOff();
        } else {
            if (heater != null) heater.turnOff();
            if (fan != null) fan.turnOn();
        }
    }
}
```

Compute the following metrics for `SmartThermostat` (show intermediate steps):
1. McCabe's version of **Weighted Methods per Class (`WMC`)**. **[2 Marks]**
2. **Lack of Cohesion of Methods 2 (`LCOM2`)**, using $LCOM2(x) = 1 - \frac{\sum ma_i}{m \cdot a}$. **[2 Marks]**  
**[Total: 4 Marks | CO2 | C3: Apply | BNQF: Thinking – Cognitive]**

**(c)** Interpret the `LCOM2` value computed in **2.(b)** with respect to class cohesion quality, and explain why an `LCOM3` value greater than $1$ ($\text{LCOM3} = \frac{m - \sum ma_i / a}{m - 1} > 1$) indicates the presence of "dead attributes."  
**[2 Marks | CO2 | C2: Understanding | BNQF: Fundamental – Understanding]**

---

### **Question 3** *(8 Marks — Software Architecture & Architectural Styles)*

**(a)** State the strict definition of *software architecture* (Clements et al., 2010) and list any **two** architectural styles from Garlan & Shaw's classification.  
**[2 Marks | CO3 | C1: Knowledge | BNQF: Fundamental – Knowledge]**

**(b)** Explain the responsibilities of the three components in the **Model–View–Controller (MVC)** architectural style, and identify which two components together constitute the User Interface (UI).  
**[3 Marks | CO3 | C2: Understanding | BNQF: Fundamental – Understanding]**

**(c)** *Scenario:* A clinical diagnostics platform integrates six independent specialist engines (`ks1`–`ks6`: radiology, bloodwork, symptoms, genomics, drug-interaction, and history) that must collaboratively read and update partial findings on a shared patient case store without calling one another directly.  
Evaluate and recommend the most appropriate **architectural style** for this platform, justify why it is preferable to a **Layered architecture** here, and briefly describe how the components interact.  
**[3 Marks | CO3 | C5: Evaluate | BNQF: Thinking – Cognitive]**

---

### **Question 4** *(8 Marks — GoF & Enterprise Application Architecture Patterns)*

**(a)** List the **three** classification categories of Gang of Four (GoF) design patterns and name **one** pattern belonging to each category.  
**[2 Marks | CO4 | C1: Knowledge | BNQF: Fundamental – Knowledge]**

**(b)** Compare **Single Table Inheritance** and **Class Table Inheritance** for mapping an object-oriented inheritance hierarchy to relational database tables, stating **one advantage** and **one disadvantage** of each approach.  
**[2 Marks | CO4 | C2: Understanding | BNQF: Fundamental – Understanding]**

**(c)** Identify the specific **GoF design pattern** best suited for each scenario below and briefly describe the structural role of the pattern's key class(es):
1. A document editor must attach scrollbars and borders dynamically to a `TextView` object at runtime without creating a subclass explosion for every feature combination. **[2 Marks]**
2. The editor must integrate a third-party `LegacyPdfRenderer` library whose method `renderDocument(byte[])` does not match the editor's expected `Graphic.draw()` interface, without modifying the library's existing code. **[2 Marks]**  
**[Total: 4 Marks | CO4 | C3: Apply | BNQF: Thinking – Cognitive]**

---

### **Question 5** *(8 Marks — User Interface Design & Accessibility)*

**(a)** List any **four** fundamental UI design principles and any **two** UI interaction modalities covered in the course.  
**[2 Marks | CO5 | C1: Knowledge | BNQF: Fundamental – Knowledge]**

**(b)** Explain why a UI must not rely on **color alone** to convey critical software status, and describe **two** color-presentation guidelines that support users with color blindness or visual impairments.  
**[3 Marks | CO5 | C2: Understanding | BNQF: Fundamental – Understanding]**

**(c)** Analyze the following legacy interface scenario, identify **three distinct UI design or presentation violations** (naming the violated principle/guideline for each), and propose a concrete fix for each:
> *Scenario:* A billing clerk sees 40 ungrouped input boxes labeled with cryptic internal database codes (`TQP STAT`, `CAT CD`, `FT/PT`). Clicking "Delete" removes a record immediately with no confirmation or undo support, and overdue invoices are indicated solely by changing a small status dot from green to red.  
**[3 Marks | CO5 | C4: Analyze | BNQF: Thinking – Cognitive]**

---

## 3. Question-to-CO, Bloom's Taxonomy & BNQF Mapping Matrix

| Q. No. | Topic Assessed | Marks | Mapped CO | Bloom's Level (Cognitive Process) | Knowledge / Thinking Tier | Knowledge Dimension | BNQF Level 7 Domain |
| :---: | :--- | :---: | :---: | :--- | :--- | :--- | :--- |
| **1.(a)** | Abstraction & Encapsulation | 2 | **CO1** | `C1` – Remember | **Knowledge** (LOTS) | Factual / Conceptual | Fundamental (*Knowledge*) |
| **1.(b)** | Design Phases & Rationale | 3 | **CO1** | `C2` – Understand | **Understanding** (LOTS) | Conceptual | Fundamental (*Understanding*) |
| **1.(c)** | Cohesion Classification & Analysis | 3 | **CO1** | `C4` – Analyze | **Cognitive** (HOTS) | Conceptual / Procedural | Thinking (*Cognitive*) |
| **2.(a)** | CK Metrics (`CBO`, `RFC`) | 2 | **CO2** | `C1` – Remember | **Knowledge** (LOTS) | Factual | Fundamental (*Knowledge*) |
| **2.(b)** | Calculating `WMC` & `LCOM2` | 4 | **CO2** | `C3` – Apply | **Cognitive** (HOTS) | Procedural | Thinking (*Cognitive*) |
| **2.(c)** | Interpreting `LCOM2` & `LCOM3 > 1` | 2 | **CO2** | `C2` – Understand | **Understanding** (LOTS) | Conceptual | Fundamental (*Understanding*) |
| **3.(a)** | Architecture Def. & Styles | 2 | **CO3** | `C1` – Remember | **Knowledge** (LOTS) | Factual | Fundamental (*Knowledge*) |
| **3.(b)** | MVC Responsibilities & UI | 3 | **CO3** | `C2` – Understand | **Understanding** (LOTS) | Conceptual | Fundamental (*Understanding*) |
| **3.(c)** | Selecting Architectural Style | 3 | **CO3** | `C5` – Evaluate | **Cognitive** (HOTS) | Conceptual / Procedural | Thinking (*Cognitive*) |
| **4.(a)** | GoF Pattern Classification | 2 | **CO4** | `C1` – Remember | **Knowledge** (LOTS) | Factual | Fundamental (*Knowledge*) |
| **4.(b)** | Inheritance ORM Trade-offs | 2 | **CO4** | `C2` – Understand | **Understanding** (LOTS) | Conceptual | Fundamental (*Understanding*) |
| **4.(c)** | Applying Decorator & Adapter | 4 | **CO4** | `C3` – Apply | **Cognitive** (HOTS) | Procedural / Conceptual | Thinking (*Cognitive*) |
| **5.(a)** | UI Principles & Modalities | 2 | **CO5** | `C1` – Remember | **Knowledge** (LOTS) | Factual | Fundamental (*Knowledge*) |
| **5.(b)** | Color Accessibility Guidelines | 3 | **CO5** | `C2` – Understand | **Understanding** (LOTS) | Conceptual | Fundamental (*Understanding*) / Social |
| **5.(c)** | Auditing UI Design Violations | 3 | **CO5** | `C4` – Analyze | **Cognitive** (HOTS) | Conceptual / Procedural | Thinking (*Cognitive*) / Social |
| **Total** | | **40** | **CO1–CO5** | **C1–C5** | **Knowledge / Understanding / Higher Cognitive** | | **BNQF Level 7 Compliant** |

---

## 4. Table of Specifications (ToS) — Marks Distribution

| Course Outcome | Knowledge (`C1`: Remember) | Understanding (`C2`: Understand) | Higher Cognitive (`C3`: Apply) | Higher Cognitive (`C4`: Analyze) | Higher Cognitive (`C5`: Evaluate) | Total Marks | Weightage |
| :--- | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| **CO1** | 2 | 3 | 0 | 3 | 0 | **8** | 20.0% |
| **CO2** | 2 | 2 | 4 | 0 | 0 | **8** | 20.0% |
| **CO3** | 2 | 3 | 0 | 0 | 3 | **8** | 20.0% |
| **CO4** | 2 | 2 | 4 | 0 | 0 | **8** | 20.0% |
| **CO5** | 2 | 3 | 0 | 3 | 0 | **8** | 20.0% |
| **Total Marks** | **10** | **13** | **8** | **6** | **3** | **40** | **100%** |
| **Tier Share** | **25.0%** *(Knowledge)* | **32.5%** *(Understanding)* | **42.5%** *(Higher Cognitive: C3–C5)* ||| **40** | **100%** |

> **BNQF Level 7 Alignment Summary:**
> - **Lower-Order Thinking Skills (LOTS — Knowledge `C1` + Understanding `C2`):** **23 Marks (57.5%)** — verifies foundational command of design terminology, principles, metrics, styles, and patterns.
> - **Higher-Order Thinking Skills (HOTS — Cognitive `C3`–`C5`):** **17 Marks (42.5%)** — verifies independent application, quantitative analysis, architectural evaluation, and critical problem-solving expected of a Bachelor's-level (`BNQF Level 7`) Software Engineering graduate.

---

## 5. Concise Marking Key (Examiner Rubric)

- **Q1 (8 Marks):**
  - `1(a)` **[2]**: Abstraction = focused view retaining purpose-relevant info and ignoring the rest [1]; Encapsulation/Info Hiding = packaging internal details and making them inaccessible externally [1].
  - `1(b)` **[3]**: Architectural = organizing software into components [1]; Detailed = specifying internal component behavior [1]; Recording rationale explains *why* nontrivial decisions were taken, supporting long-term maintainability [1].
  - `1(c)` **[3]**: Module X = *Sequential* cohesion [1]; Module Y = *Coincidental* cohesion [1]; Justification: Sequential cohesion links elements via a data-flow chain (high cohesion), whereas Coincidental groups unrelated parts (lowest cohesion, poor modularity) [1].
- **Q2 (8 Marks):**
  - `2(a)` **[2]**: `CBO(A)` = number of classes used by `A` (inheritance typically excluded) [1]; `RFC(A) = M + R` = local methods plus distinct called methods (standard API calls typically excluded; repeated calls counted once) [1].
  - `2(b)` **[4]**:
    - **McCabe `WMC` [2]**: `SmartThermostat` constructor ($0 + 1 = 1$), `setTargetTemp` ($0 + 1 = 1$), `regulate` ($5\text{ `if` conditions} + 1 = 6$) [1]; Total $\text{WMC} = 1 + 1 + 6 = 8$ [1].
    - **`LCOM2` [2]**: $m = 3$, $a = 4$, $ma_{\text{currentTemp}} = 2$, $ma_{\text{targetTemp}} = 3$, $ma_{\text{heater}} = 2$, $ma_{\text{fan}} = 2 \implies \sum ma_i = 9$ [1]; $LCOM2 = 1 - \frac{9}{3 \times 4} = 1 - 0.75 = 0.25$ [1].
  - `2(c)` **[2]**: `LCOM2 = 0.25` is close to `0` (smaller is better), indicating good/strong cohesion [1]; For `LCOM3 > 1`, $\frac{m - \sum ma_i / a}{m - 1} > 1 \implies \sum ma_i / a < 1$, which is only possible when one or more attributes have $ma_i = 0$ (unused/dead attributes) [1].
- **Q3 (8 Marks):**
  - `3(a)` **[2]**: Architecture = set of structures needed to reason about the system (elements, relations, properties of both) [1]; Any 2 styles from Layered, Event-Based, MVC, Blackboard/Repository, Pipes and Filters [1].
  - `3(b)` **[3]**: Model = domain state/information; View = UI display of model; Controller = handles user input, updates model, refreshes view [2]; UI = View + Controller [1].
  - `3(c)` **[3]**: Recommend **Blackboard / Repository** [1]; Preferable to Layered because independent engines do not depend hierarchically on adjacent layers [1]; Knowledge sources (`ks1`–`ks6`) read/write asynchronously via direct access to the shared central Blackboard [1].
- **Q4 (8 Marks):**
  - `4(a)` **[2]**: Creational (e.g., Factory Method/Prototype/Singleton), Structural (e.g., Adapter/Composite/Facade/Decorator), Behavioral (e.g., Strategy/Template Method/Command/Observer) — 3 categories + 1 valid pattern each [2].
  - `4(b)` **[2]**: Single Table Inheritance: 1 table, no joins/easy refactoring (+) vs. null/irrelevant fields & wasted space (−) [1]; Class Table Inheritance: 1 table per class via FKs, no wasted space/clear mapping (+) vs. joins needed & refactoring alters DB (−) [1].
  - `4(c)` **[4]**:
    1. **Decorator** [1]; `Decorator` implements `VisualComponent`, wraps a `Component` reference, and overrides `Draw()` to add border/scroll behavior dynamically at runtime [1].
    2. **Object Adapter** (or **Adapter**) [1]; `Adapter` implements `Graphic` (`Target`), holds/wraps `LegacyPdfRenderer` (`Adaptee`), and delegates `draw()` to `renderDocument(...)` without modifying library code [1].
- **Q5 (8 Marks):**
  - `5(a)` **[2]**: Any 4 principles (Learnability, User familiarity, Consistency, Minimal surprise, Recoverability, User guidance, User diversity) [1]; Any 2 modalities (Question-answer, Direct manipulation, Menu selection, Form fill-in, Command language, Natural language) [1].
  - `5(b)` **[3]**: Color blindness/deficiency and poor eyesight mean color-only cues fail (*User diversity*) [1]; Any 2 guidelines: avoid green–red combinations and vary saturation/brightness [1]; limit total colors and pair consistent color-coding with text/icons [1].
  - `5(c)` **[3]**: (1) *User familiarity / Learnability*: cryptic DB codes & ungrouped fields → use clear domain labels and logical grouping [1]; (2) *Recoverability / Minimal surprise*: instant irreversible delete → add confirmation + undo [1]; (3) *User diversity / Color presentation*: green-red dot alone → avoid green-red pairs and include text/icon status badge [1].
