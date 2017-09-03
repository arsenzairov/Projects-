import cs50


def main():
    while True:
        print("Height: ")
        height = cs50.get_int()
        if height >= 0 and height <= 23:
            break

    for i in range(height):
        for j in range(height - i - 1):
            print(" ")
            for k in range(i + 2):
                print("#")
                print("")

        if __name__ == "__main__":
            main()