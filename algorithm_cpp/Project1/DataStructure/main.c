#define QUEUE

#ifdef QUEUE
extern void linear_queue();
extern void ring_buffer();
extern void double_ended_queue();
#endif

int main() {
	double_ended_queue();
}