def print_nutritions():
    name = input("이름: ")
    height = float(input("키: "))
    weight = float(input("체중: "))
    protein = weight * 1.6
    BMI = weight / pow(height / 100, 2)

    print()
    print("----------------------------- [결과] -----------------------------")

    print("{0}님은 하루 {1:.2f}g의 단백질 섭취가 필요합니다.".format(name, protein))
    print("한끼당 {0:.2f}g은 섭취해야겠네요!".format(protein / 3))
    print()
    print("{0}님의 BMI 수치는 {1:.2f}입니다.".format(name, BMI))

    if BMI > 25:
        print("BMI {0:.2f}은(는) 비만에 해당합니다.".format(BMI))
        print("식습관을 개선하고 유산소 운동과 근력운동을 겸해야합니다.")
    elif BMI > 23:
        print("BMI {0:.2f}은(는) 과체중에 해당합니다.".format(BMI))
        print("조금만 더 체중을 감량해보세요!")
    elif BMI > 18.5:
        print("BMI {0:.2f}은(는) 정상에 해당합니다.".format(BMI))
        print("잘하고있어요!")
    else:
        print("BMI {0:.2f}은(는) 저체중에 해당합니다.".format(BMI))
        print("체중을 조금 늘려보세요!")

    print("------------------------------------------------------------------")
    print()

    goal_BMI = float(input("목표 BMI: "))
    goal_weight = (weight * goal_BMI) / BMI
    weight_interval = weight - goal_weight

    if weight_interval >= 0:
        print("BMI {0:.2f}이(가) 되려면 {1:.2f}kg을 감량해야해요!".format(
            goal_BMI, weight_interval))
    else:
        print("BMI {0:.2f}이(가) 되려면 {1:.2f}kg을 증량해야해요!".format(
            goal_BMI, -weight_interval))
    print("현재 체중: {0:.2f}kg\t목표 체중: {1:.2f}kg".format(weight, goal_weight))
    print()


if __name__ == "__main__":
    print_nutritions()
